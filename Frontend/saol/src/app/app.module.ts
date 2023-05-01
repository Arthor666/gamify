import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { EquipoAdminComponent } from './component/EquipoAdmin.component';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { MatFormField, MatLabel } from '@angular/material/form-field';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatInputModule } from '@angular/material/input';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { MatChipsModule } from '@angular/material/chips';
import { MatIconModule } from '@angular/material/icon';
import { ProyectoAdminComponent } from './component/ProyectoAdmin.component';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatDialogModule } from '@angular/material/dialog';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { RecompensaAdminComponent } from './component/RecompensaAdmin.component';
import { TareaComponent } from './component/Tarea.component';
import { MatSelectModule } from '@angular/material/select';
import { UsuarioRecompensaComponent } from './component/UsuarioRecompensa.component';
import { UsuarioRecompensaAdminComponent } from './component/UsuarioRecompensaAdmin.component';
import { KanbanComponent } from './component/Kanban.component';
import { DragDropModule } from '@angular/cdk/drag-drop';
import { CreateKanbanColumnDialog } from './component/CreateKanbanColumnDialog.component';
import { UsuarioComponent } from './component/Usuario.component';
import { EquipoComponent } from './component/Equipo.component';
import { MatToolbarModule } from '@angular/material/toolbar';
import { DialogNotificacionComponent } from './component/DialogNotificaciones.component';
import { MatTooltipModule } from '@angular/material/tooltip';
import { RecompensaComponent } from './component/Recompensa.component';
import * as echarts from 'echarts';
import { NgxEchartsModule } from 'ngx-echarts';
import { DialogFlujoAcumuladoComponent } from './component/DialogFlujoAcumulado.component';
import { MatButtonModule } from '@angular/material/button';
import { DatePipe } from '@angular/common';
import { ProyectoComponent } from './component/Proyecto.component';
import { GrupoAdminComponent } from './component/GrupoAdmin.component';
import { GrupoComponent } from './component/Grupo.component';
import { DialogInscribirGrupoComponent } from './component/DialogInscribirGrupo.component';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { SigninComponent } from './component/Signin.component';
import { MatCardModule } from '@angular/material/card';
import { LoginComponent } from './component/Login.component';
import { MatRadioModule } from '@angular/material/radio';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatBadgeModule } from '@angular/material/badge';
import { MenuPrincipalComponent } from './component/MenuPrincipal.component';
import { MatDividerModule } from '@angular/material/divider';
import { MatListModule } from '@angular/material/list';
import { MenuEquipoComponent } from './component/MenuEquipo.component';
import { DialogProyectoComponent } from './component/DialogProyecto.component';
import { HistoriasUsuarioComponent } from './component/HistoriasUsuario.component';
import { MatMenuModule } from '@angular/material/menu';
import { DialogDiagramaQuemadoComponent } from './component/DialogDiagramaQuemado.component';
import { NgOptimizedImage } from '@angular/common';
import { ModifyUserComponent } from './component/ModifyUser.component';
import { DialogRecuperarCuentaComponent } from './component/DialogRecuperarCuenta.component';
import { TokenInterceptor } from './service/TokenInterceptor.service';








//import { NavBarComponent } from './component/NavBar.component';






@NgModule({
  declarations: [
    AppComponent,
    EquipoAdminComponent,
    ProyectoAdminComponent,
    RecompensaAdminComponent,
    TareaComponent,
    UsuarioRecompensaComponent,
    UsuarioRecompensaAdminComponent,
    KanbanComponent,
    CreateKanbanColumnDialog,
    UsuarioComponent,
    EquipoComponent,
    DialogNotificacionComponent,
    RecompensaComponent,
    DialogFlujoAcumuladoComponent,
    ProyectoComponent,
    GrupoAdminComponent,
    GrupoComponent,
    DialogInscribirGrupoComponent,
    SigninComponent,
    LoginComponent,
    MenuPrincipalComponent,
    MenuEquipoComponent,
    DialogProyectoComponent,
    HistoriasUsuarioComponent,
    DialogDiagramaQuemadoComponent,
    ModifyUserComponent,
    DialogRecuperarCuentaComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    MatInputModule,
    MatAutocompleteModule,
    MatChipsModule,
    HttpClientModule,
    MatIconModule,
    MatDatepickerModule,
    MatNativeDateModule,
    FormsModule,
    MatPaginatorModule,
    MatTableModule,
    MatExpansionModule,
    MatSnackBarModule,
    MatSlideToggleModule,
    MatSelectModule,
    DragDropModule,
    MatDialogModule,
    MatToolbarModule,
    MatTooltipModule,
    NgxEchartsModule.forRoot({
      echarts
    }),
    MatButtonModule,
    MatProgressSpinnerModule,
    MatCardModule,
    MatRadioModule,
    MatGridListModule,
    MatBadgeModule,
    MatDividerModule,
    MatListModule,
    MatMenuModule,
    NgOptimizedImage 
  ],
  exports:[
       
  ],
  providers: [{ provide: HTTP_INTERCEPTORS, useClass: TokenInterceptor,multi: true }],
  bootstrap: [AppComponent]
})
export class AppModule { }
