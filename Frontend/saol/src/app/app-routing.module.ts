import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EquipoComponent } from './component/Equipo.component';
import { EquipoAdminComponent } from './component/EquipoAdmin.component';
import { GrupoComponent } from './component/Grupo.component';
import { GrupoAdminComponent } from './component/GrupoAdmin.component';
import { KanbanComponent } from './component/Kanban.component';
import { SigninComponent } from './component/Signin.component';
import { ProyectoComponent } from './component/Proyecto.component';
import { ProyectoAdminComponent } from './component/ProyectoAdmin.component';
import { RecompensaComponent } from './component/Recompensa.component';
import { RecompensaAdminComponent } from './component/RecompensaAdmin.component';
import { TareaComponent } from './component/Tarea.component';
import { UsuarioComponent } from './component/Usuario.component';
import { UsuarioRecompensaComponent } from './component/UsuarioRecompensa.component';
import { UsuarioRecompensaAdminComponent } from './component/UsuarioRecompensaAdmin.component';
import { LoginComponent } from './component/Login.component';
import { authGuardAlumnos, authGuardCommons, authGuardProfesores, authGuardPublic} from './auth/auth.guard';
import { MenuPrincipalComponent } from './component/MenuPrincipal.component';
import { MenuEquipoComponent } from './component/MenuEquipo.component';
import { HistoriasUsuarioComponent } from './component/HistoriasUsuario.component';
import { ModifyUserComponent } from './component/ModifyUser.component';


const publicRoutes: Routes = [
  { path: 'signin', component: SigninComponent, canActivate: [authGuardPublic] },
  { path: 'login', component: LoginComponent, canActivate: [authGuardPublic] }
]
const commonRoutes: Routes = [  
  { path: 'menu', component: MenuPrincipalComponent, canActivate: [authGuardCommons] },
  { path: 'modificar', component: ModifyUserComponent, canActivate: [authGuardCommons] },  
];
const alumnoRoutes: Routes = [
  { path: 'tarea/:idEquipo', component: TareaComponent, canActivate: [authGuardAlumnos] },
  { path: 'kanban/:idEquipo', component: KanbanComponent, canActivate: [authGuardAlumnos] },
  { path: 'menuEquipo/:idEquipo', component: MenuEquipoComponent, canActivate: [authGuardAlumnos] },
  { path: 'historia/:idEquipo', component: HistoriasUsuarioComponent, canActivate: [authGuardAlumnos] },
  { path: 'grupo', component: GrupoComponent, canActivate: [authGuardAlumnos] },
  { path: 'proyecto', component: ProyectoComponent, canActivate: [authGuardAlumnos] },
  { path: 'equipo', component: EquipoComponent, canActivate: [authGuardAlumnos] }
];
const profesorRoutes: Routes = [
  { path: 'equipoAdmin/:idGrupo', component: EquipoAdminComponent, canActivate: [authGuardProfesores] },
  { path: 'proyectoAdmin', component: ProyectoAdminComponent, canActivate: [authGuardProfesores] },
  { path: 'recompensaAdmin', component: RecompensaAdminComponent, canActivate: [authGuardProfesores] },    
  { path: 'usuarioAdmin', component: UsuarioComponent, canActivate: [authGuardProfesores] },  
  { path: 'grupoAdmin', component: GrupoAdminComponent, canActivate: [authGuardProfesores] },    

];

@NgModule({
  imports: [RouterModule.forRoot(profesorRoutes), RouterModule.forRoot(alumnoRoutes), RouterModule.forRoot(commonRoutes), RouterModule.forRoot(publicRoutes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
