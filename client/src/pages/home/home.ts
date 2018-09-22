import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';
import { HttpClientModule } from '@angular/common/http';
import { HttpClient } from '@angular/common/http';
import { AlertController } from 'ionic-angular';

interface aluno {
  nome: string,
  matricula: string,
  idade: string
}


@Component({
  selector: 'page-home',
  templateUrl: 'home.html'
})
export class HomePage {
  url: string = "http://127.0.0.1:8081/loopis-api/api/aluno";

  aluno: aluno = {
    nome: "",
    matricula: "",
    idade: ""
  };
  todos: Array<aluno> = [];

  constructor(public http: HttpClient, public navCtrl: NavController,
    public alert: AlertController) {

    this.atualizarTodos();
  }

  cadastrarAluno() {
    console.log(this.url);
    this.http.post(this.url, this.aluno, { observe: 'response' }).subscribe(res => {
      if (res.status == 200) {
        this.alert.create({
          title: 'Aluno cadastrado!',
          buttons: ['OK']
        }).present();
      }
      this.atualizarTodos();
    });
    
  }

  deletarAluno(matricula) {
    this.http.delete(this.url.concat("/" + matricula), { observe: 'response' }).subscribe(res => {
      console.log(res.status);
      this.atualizarTodos();
      if (res.status == 200) {
        this.alert.create({
          title: 'Aluno deletado!',
          buttons: ['OK']
        }).present();
      }
    });
  }
  atualiza(){
    this.http.put(this.url,this.aluno,{observe:'response'}).subscribe(res=>{
      console.log(res.status);
      this.atualizarTodos();
    });
  }
  atualizarAluno(a){
    this.aluno = a;
    document.getElementById("btAtualizar").style.display="inline";
  }

  atualizarTodos() {
    this.http.get(this.url, { observe: 'response' }).subscribe(res => {
      this.todos = res.body;
    });
  }
}
