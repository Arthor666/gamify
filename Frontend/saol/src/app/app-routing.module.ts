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
import { authGuardAlumnos} from './auth/auth.guard';
import { MenuPrincipalComponent } from './component/MenuPrincipal.component';
import { MenuEquipoComponent } from './component/MenuEquipo.component';
import { HistoriasUsuarioComponent } from './component/HistoriasUsuario.component';
import { ModifyUserComponent } from './component/ModifyUser.component';

const commonRoutes: Routes = [
  { path: 'menu', component: MenuPrincipalComponent },
  { path: 'modificar', component: ModifyUserComponent }
];
const alumnoRoutes: Routes = [
  { path: 'tarea/:idEquipo', component: TareaComponent, canActivate: [authGuardAlumnos] },
  { path: 'kanban/:idEquipo', component: KanbanComponent, canActivate: [authGuardAlumnos] },
  { path: 'menuEquipo/:idEquipo', component: MenuEquipoComponent, canActivate: [authGuardAlumnos] },
  { path: 'historia/:idEquipo', component: HistoriasUsuarioComponent, canActivate: [authGuardAlumnos] },
  { path: 'grupo', component: GrupoComponent },
];
const profesorRoutes: Routes = [
  { path: 'equipoAdmin/:idGrupo', component: EquipoAdminComponent },
  { path: 'proyectoAdmin', component: ProyectoAdminComponent },
  { path: 'recompensaAdmin', component: RecompensaAdminComponent },  
  { path: 'usuario/recompensa', component: UsuarioRecompensaComponent },
  { path: 'admin/recompensa', component: UsuarioRecompensaAdminComponent },  
  { path: 'usuarioAdmin', component: UsuarioComponent },
  { path: 'equipo', component: EquipoComponent },
  { path: 'recompensa', component: RecompensaComponent },
  { path: 'proyecto', component: ProyectoComponent },
  { path: 'grupoAdmin', component: GrupoAdminComponent },  
  { path: 'signin', component: SigninComponent },
  { path: 'login', component: LoginComponent }

];

@NgModule({
  imports: [RouterModule.forRoot(profesorRoutes), RouterModule.forRoot(alumnoRoutes), RouterModule.forRoot(commonRoutes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
