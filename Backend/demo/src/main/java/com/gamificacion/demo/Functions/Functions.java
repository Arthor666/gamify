package com.gamificacion.demo.Functions;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gamificacion.demo.Enums.ConstantsEnum;
import com.gamificacion.demo.Models.Recompensa;
import com.gamificacion.demo.Models.Status;
import com.gamificacion.demo.Models.Tarea;
import com.gamificacion.demo.Models.Usuario;
import com.gamificacion.demo.Models.UsuarioRecompensa;
import com.gamificacion.demo.Repository.IRecompensaRepository;
import com.gamificacion.demo.Repository.IStatusRepository;
import com.gamificacion.demo.Repository.IUserRecompensaRepository;
import com.gamificacion.demo.Repository.IUserRepository;


@Component
public class Functions {
		
	private static IUserRepository userRepository;
	private static IRecompensaRepository recompensaRepository;
	private static IStatusRepository statusRepository;
	private static IUserRecompensaRepository userRecompensaRepository;
	
	@Autowired
	public Functions(IUserRepository userRepository,IRecompensaRepository recompensaRepository,IStatusRepository statusRepository,IUserRecompensaRepository userRecompensaRepository) {
		super();
		this.userRepository = userRepository;
		this.recompensaRepository = recompensaRepository;
		this.statusRepository = statusRepository;
		this.userRecompensaRepository = userRecompensaRepository;
		
	}




	public static void updateUserPoints(Tarea t) {
		List<Usuario> users =  userRepository.findBySubequipos_Id(t.getSubequipo().getId());		
		double puntosRecompensa = calculatePuntosRecompensa(t.getPuntosRecompensa(),t.getPorcentajePenalizacion(),t.getFechaTentativa());
		for(Usuario user: users) {			
			user.setPuntaje(user.getPuntaje() + puntosRecompensa);			
			List<Integer> ids = user.getUsuarioRecompensas().stream().map(UsuarioRecompensa::getRecompensa).collect(Collectors.toList()).stream().map(Recompensa::getId).collect(Collectors.toList());
			if(ids.isEmpty()) {
				ids.add(0);
			}
			List<Recompensa> recompensas = recompensaRepository.findByIdNotInAndPuntosLessThanEqual(ids,user.getPuntaje());
			Status s = statusRepository.findByNombre(ConstantsEnum.STATUSPORCOBRAR.toString());			
			for(Recompensa r: recompensas) {						
				UsuarioRecompensa uRecompensa = new UsuarioRecompensa();
				uRecompensa.setRecompensa(r);
				uRecompensa.setStatus(s);
				uRecompensa.setUsuario(user);
				userRecompensaRepository.save(uRecompensa);
			}
			userRepository.save(user);
		}
		
	}




	public static double calculatePuntosRecompensa(double puntosRecompensa, int porcentajePenalizacion,Timestamp fechaTentativa) {
		Date date = new Date();		
		TimeUnit time = TimeUnit.DAYS; 
        long diffrence = time.convert( (date.getTime() - fechaTentativa.getTime()) , TimeUnit.MILLISECONDS);
		if(diffrence > 0) {
			return puntosRecompensa* Math.pow( (1-((double) porcentajePenalizacion/100)) ,(int) diffrence);			
		}
		return puntosRecompensa;
	}



}
