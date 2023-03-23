import { Component, OnInit } from "@angular/core";
import pdfMake from 'pdfmake/build/pdfmake';
import pdfFonts from 'pdfmake/build/vfs_fonts';
pdfMake.vfs = pdfFonts.pdfMake.vfs;

@Component({
  templateUrl: '../html/Recompensa.component.html',
  styleUrls: ['../css/Recompensa.component.css'],
})
export class RecompensaComponent implements OnInit {

  constructor() { }

  ngOnInit() {

  }

  abrirPDF() {

    const pdfDefinition: any = {
      content: [
        {
          text: 'Solicitud de cobro de recompensa.\n\n',
          style: 'header'
            
        },
        'A quien corresponda.\n\n',
        'El usuario con nombre tal...solicita cobrar la recompensa tal...que alcanzó al juntar x puntos, por lo que al firmar el presente documento el solicitante acepta que la recompensa fue concendida por el peticionario.\n\n\n\n\n\n\n\n\n\n',
        {          
          decoration: "underline",
          characterSpacing:"5",
          text:"Solicitante:                     "
        },
        '\n\n',
        {
          decoration: "underline",
          characterSpacing: "5",
          text: "Peticionario:                    "
        }

      ],
      styles: {
        header: {
          fontSize: 18,
          bold: true
        }
      }
    }

    const pdf = pdfMake.createPdf(pdfDefinition);
    pdf.open();

  }
}
