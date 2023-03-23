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
import { Rol } from '../models/Rol';
import { RolService } from '../service/Rol.service';


@Component({
  selector: 'equipo-admin',
  templateUrl: '../html/Usuario.component.html',
  styleUrls: ['../css/Usuario.component.css'],
  animations: [
    trigger('detailExpand', [
      state('collapsed', style({ height: '0px', minHeight: '0' })),
      state('expanded', style({ height: '*' })),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ]
})
export class UsuarioComponent implements OnInit {
  title = "saol"
  usuarioFormCtl: FormGroup = new FormGroup({ nombreUsuarioCtl: new FormControl('') });
  usuarioOptionList: Array<Usuario> = [];
  usuarioNombreSelectedList: Array<string> = [];
  usuarioSelectedList: Array<Usuario> = [];
  usuarioPage!: MatTableDataSource<Usuario>;
  separatorKeysCodes: number[] = [ENTER, COMMA];
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  expandedElement: Equipo | null | undefined;
  newUsuario: any;
  displayedColumns: string[] = ['id', 'nombre', 'nombreUsuario', 'puntaje','rol','activo'];
  cadenaBuscar: string = "";
  showChip: boolean = false;
  rolList!: Rol[];
  copyEquipoPage!: MatTableDataSource<Usuario>;

  constructor(private usuarioService: UsuarioService, private equipoService: EquipoService,private rolService: RolService) {
    this.usuarioPage = new MatTableDataSource<Usuario>([new Usuario({ "nombre": "crear" })]);
    this.newUsuario = {};
  }
  ngOnInit(): void {
    this.usuarioService.getAll().subscribe(data => this.iniciarPaginacion(data));
    this.rolService.getAll().subscribe(data => this.rolList = data);
  }

  iniciarPaginacion(data: Usuario[]): void {
    this.usuarioPage.data.push(...data);
    this.usuarioPage.paginator = this.paginator;
  }

  expandir(element: any): void {
    this.newUsuario = element;
    this.expandedElement = this.expandedElement === element ? null : element;
    if (this.newUsuario.id != null) {
      this.usuarioService.getUserByEquipoId(this.newUsuario.id).subscribe(data => this.newUsuario.usuarios = data);
    }
  }

  getUsuariosOptions(event: any) {
    const name = event.target.value;
    this.usuarioService.getUserByNameAndAvailable(name).subscribe(data => this.usuarioOptionList = data);
  }
  remove(usuario: Usuario) {
    const index = this.newUsuario.usuarios.findIndex((x: Usuario) => x.id == usuario.id);
    if (index != -1) {
      this.newUsuario.usuarios.splice(index, 1);
    }
  }

  guardar() {
    this.usuarioService.save(this.newUsuario).subscribe(data => console.log(data));

  }

  selected(event: MatAutocompleteSelectedEvent) {
    const exist = this.newUsuario.usuarios.indexOf(event.option.value);
    if (exist == -1) {
      this.newUsuario.usuarios.push(event.option.value);
    }
  }

  buscar() {
    this.usuarioService.getUserByName(this.cadenaBuscar).subscribe(data => { this.copyEquipoPage = this.usuarioPage; this.usuarioPage = new MatTableDataSource<Usuario>(data) });
    this.showChip = true;
    this.cadenaBuscar = "";
  }
  deleteBusqueda() {
    this.usuarioPage = this.copyEquipoPage;
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
