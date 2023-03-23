import { Component, OnInit, ViewChild } from '@angular/core';
import { FormArray, FormControl, FormGroup } from '@angular/forms';
import { Usuario } from '../models/Usuario';
import { UsuarioService } from '../service/Usuario.service';
import { MatAutocompleteSelectedEvent } from '@angular/material/autocomplete';
import { MatChipInputEvent } from '@angular/material/chips';
import { COMMA, ENTER } from '@angular/cdk/keycodes';
import { animate, state, style, transition, trigger } from '@angular/animations';
import { Equipo } from '../models/Equipo';
import { EquipoService } from '../service/Equipo.service';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';


@Component({
  selector: 'equipo-admin',
  templateUrl: '../html/EquipoAdmin.component.html',
  styleUrls: ['../css/EquipoAdmin.component.css'],
  animations: [
    trigger('detailExpand', [
      state('collapsed', style({ height: '0px', minHeight: '0' })),
      state('expanded', style({ height: '*' })),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ]
})
export class EquipoAdminComponent implements OnInit {
  title = "saol"
  usuarioFormCtl: FormGroup = new FormGroup({ nombreUsuarioCtl:new  FormControl('') });
  usuarioOptionList: Array<Usuario> = [];
  usuarioNombreSelectedList: Array<string> = [];
  usuarioSelectedList: Array<Usuario> = [];
  equipoPage!: MatTableDataSource<Equipo>;
  separatorKeysCodes: number[] = [ENTER, COMMA];
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  expandedElement: Equipo | null | undefined;
  newEquipo: any;
  displayedColumns: string[] = ['id', 'nombre', 'fechaCreacion', 'activo'];
  cadenaBuscar: string = "";
  showChip: boolean = false;
  copyEquipoPage!: MatTableDataSource<Equipo>;

  constructor(private usuarioService: UsuarioService, private equipoService: EquipoService) {
    this.equipoPage = new MatTableDataSource<Equipo>([new Equipo({ "nombre": "crear" })]);
    this.newEquipo = {};
  }
  ngOnInit(): void {
    this.equipoService.getAll().subscribe(data => this.iniciarPaginacion(data));
  }

  iniciarPaginacion(data: Equipo[]): void {
    this.equipoPage.data.push(...data);
    this.equipoPage.paginator = this.paginator;    
  }

  expandir(element: any): void {
    this.newEquipo = element;    
    this.expandedElement = this.expandedElement === element ? null : element;
    if (this.newEquipo.id != null) {
      this.equipoService.getCountProyectos(this.newEquipo.id).subscribe(count => {
        this.newEquipo.numeroProyectos = count.count;
        this.usuarioService.getUserByEquipoId(this.newEquipo.id).subscribe(data => this.newEquipo.usuarios = data);
      });      
    }    
  }

  getUsuariosOptions(event:any) {
    const name = event.target.value;
    this.usuarioService.getUserByNameAndAvailable(name).subscribe(data => this.usuarioOptionList = data);    
  }
  remove(usuario: Usuario) {
    const index = this.newEquipo.usuarios.findIndex((x: Usuario) => x.id == usuario.id);    
    if (index != -1) {
      this.newEquipo.usuarios.splice(index, 1);      
    }    
  }

  guardar() {
    this.equipoService.saveEquipo(this.newEquipo).subscribe(data => console.log(data));

  }

  selected(event: MatAutocompleteSelectedEvent) {
    const exist = this.newEquipo.usuarios.indexOf(event.option.value);
    if (exist == -1) {
      this.newEquipo.usuarios.push(event.option.value);
    }    
  }

  buscar() {
    this.equipoService.getByNombreLike(this.cadenaBuscar).subscribe(data => { this.copyEquipoPage = this.equipoPage; this.equipoPage = new MatTableDataSource<Equipo>(data) });
    this.showChip = true;
    this.cadenaBuscar = "";
  }
  deleteBusqueda() {
    this.equipoPage = this.copyEquipoPage;
    this.showChip = false;
  }


  onSubmit(): void {
    const aux: any = {};
    aux.nombre = this.usuarioFormCtl.controls['nombreUsuarioCtl'].value;
    aux.id = null;
    aux.fechaCreacion = null;
    aux.isActive = 1;
    aux.usuarios = this.usuarioSelectedList;
    aux.proyectos = null;
    const equipo: Equipo = new Equipo(aux);
    this.equipoService.saveEquipo(equipo).subscribe(data => console.log(data));
    }
}
