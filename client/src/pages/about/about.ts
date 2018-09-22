import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';
import { HttpClientModule } from '@angular/common/http';
import { HttpClient } from '@angular/common/http';
import { AlertController } from 'ionic-angular';

interface professor {
  nome: string,
  matricula: string,
  salario: string
}

@Component({
  selector: 'page-about',
  templateUrl: 'about.html'
})
export class AboutPage {

  professor : professor= {
    nome:"",
    matricula:"",
    salario:""
  };
  todos: Array<professor> = [];
  url: string = "http://127.0.0.1:8081/loopis-api/api/professor";

  constructor(public navCtrl: NavController, public http: HttpClient,
     public alert:AlertController) {
      this.atualizarTodos();
  }
  cadastrarProfessor() {
    console.log(this.url);
    this.http.post(this.url, this.professor, { observe: 'response' }).subscribe(res => {
      if (res.status == 200) {
        this.alert.create({
          title: 'Professor cadastrado!',
          buttons: ['OK']
        }).present();
      }
      this.atualizarTodos();
      this.limpar();
    });
    
  }

  deletarProfessor(matricula) {
    this.http.delete(this.url.concat("/" + matricula), { observe: 'response' }).subscribe(res => {
      console.log(res.status);
      this.atualizarTodos();
      if (res.status == 200) {
        this.alert.create({
          title: 'Professor deletado!',
          buttons: ['OK']
        }).present();
      }
    });
  }
  atualiza(){
    this.http.put(this.url,this.professor,{observe:'response'}).subscribe(res=>{
      console.log(res.status);
      this.atualizarTodos();
      this.limpar();
      this.alert.create({
        title: 'Professor atualizado!',
        buttons: ['OK']
      }).present();
    });
  }
  atualizarProfessor(p){
    this.professor = p;
    document.getElementById("btAtualizarProfessor").style.display="inline";
  }

  atualizarTodos() {
    this.http.get(this.url, { observe: 'response' }).subscribe(res => {
      this.todos = res.body;
    });
  }

  limpar(){
    this.professor = {nome:"",matricula:"",salario:""};
  }
}
