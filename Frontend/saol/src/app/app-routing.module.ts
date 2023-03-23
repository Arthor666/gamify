import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EquipoComponent } from './component/Equipo.component';
import { EquipoAdminComponent } from './component/EquipoAdmin.component';
import { KanbanComponent } from './component/Kanban.component';
import { ProyectoAdminComponent } from './component/ProyectoAdmin.component';
import { RecompensaComponent } from './component/Recompensa.component';
import { RecompensaAdminComponent } from './component/RecompensaAdmin.component';
import { TareaComponent } from './component/Tarea.component';
import { UsuarioComponent } from './component/Usuario.component';
import { UsuarioRecompensaComponent } from './component/UsuarioRecompensa.component';
import { UsuarioRecompensaAdminComponent } from './component/UsuarioRecompensaAdmin.component';

const routes: Routes = [
  { path: 'equipo/equipoAdmin', component: EquipoAdminComponent },
  { path: 'proyecto/proyectoAdmin', component: ProyectoAdminComponent },
  { path: 'recompensa/recompensaAdmin', component: RecompensaAdminComponent },
  { path: 'tarea', component: TareaComponent },
  { path: 'usuario/recompensa', component: UsuarioRecompensaComponent },
  { path: 'admin/recompensa', component: UsuarioRecompensaAdminComponent },
  { path: 'kanban', component: KanbanComponent },
  { path: 'usuario/usuarioAdmin', component: UsuarioComponent },
  { path: 'equipo', component: EquipoComponent },
  { path: 'recompensa', component: RecompensaComponent }


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
