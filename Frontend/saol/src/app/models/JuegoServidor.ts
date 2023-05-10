import { Equipo } from "./Equipo";

export class JuegoServidor{
    levelRam : number ;
    levelHdd : number ;
    levelRed : number ;
    levelServer : number ;
    equipo: Equipo;
    monedas: number;
    constructor(juegoServidor: any){
        this.levelRam = juegoServidor.levelRam;
        this.levelRed = juegoServidor.levelRed;
        this.levelHdd = juegoServidor.levelHdd;
        this.levelServer = juegoServidor.levelServer;
        this.equipo = juegoServidor.equipo;
        this.monedas = juegoServidor.monedas;
    }


}