import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';
import { HomePage } from '../home/home';
import { HttpClient } from '@angular/common/http';
import { AlertController } from 'ionic-angular';

interface aluno {
  nome: string,
  matricula: string,
  idade: string
}
interface professor {
  nome: string,
  matricula: string,
  salario: string
}

interface Turma {
  nome: string,
  id: string,
  alunos: any,
  professor: professor,
  cargahoraria: string
}

@Component({
  selector: 'page-contact',
  templateUrl: 'contact.html'
})
export class ContactPage {

  turmas: any = [];

  turma: Turma = {
    nome: "",
    id:null,
    cargahoraria: "",
    professor: { nome: "", matricula: "", salario: "" },
    alunos: []
  };
  professores: any = [];

  url: string = "http://127.0.0.1:8081/loopis-api/api/";
  constructor(public navCtrl: NavController, public http: HttpClient,
    public alert: AlertController) {

    this.http.get(this.url + "aluno", { observe: 'response' }).subscribe(res => {
      this.turma.alunos = res.body;
    });
    this.atualizarprofessores();
    this.atualizaturmas();

  }

  cadastrarTurma() {
    console.log(this.turma);
    this.turma.professor = this.professores[0];
    this.http.post(this.url.concat("turma"), this.turma, { observe: 'response' }).subscribe(res => {
      if (res.status == 200) {
        this.alert.create({
          title: 'turma cadastrada!',
          buttons: ['OK']
        }).present();
      }
    });
    this.atualizaturmas();
  }

  atualizaturmas() {
    this.http.get(this.url + "turma", { observe: 'response' }).subscribe(res => {
      this.turmas = res.body;
      console.log(this.turmas);
    });
  }

  atualizarprofessores() {
    this.http.get(this.url + "professor", { observe: 'response' }).subscribe(res => {
      console.log(res);
      this.professores = res.body;
    });
  }

  deletarturma(id) {
    this.http.delete(this.url + "turma/" + id, { observe: 'response' }).subscribe(res => {
      console.log(res.status);
      this.atualizaturmas();
    });
  }

  atualiza() {
    this.http.put(this.url+"turma", this.turma, { observe: 'response' }).subscribe(res => {
      console.log(res.status);
      this.atualizaturmas();
    });
  }

  atualizaturma(t) {
    this.turma = t;
    document.getElementById("btturma").style.display = "inline";
  }
}
