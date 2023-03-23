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
import { HttpClientModule } from '@angular/common/http';
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
    RecompensaComponent
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
    MatTooltipModule 
  ],
  exports:[
    MatInputModule,
    MatAutocompleteModule,
    MatChipsModule,
    MatIconModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatTableModule,
    MatPaginatorModule,
    MatExpansionModule,
    MatSnackBarModule,
    MatSlideToggleModule,
    MatSelectModule,
    MatToolbarModule,
    MatTooltipModule 
    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
