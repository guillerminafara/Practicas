import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '@modules/auth/services/auth.service';
import { CookieService } from 'ngx-cookie-service';


@Component({
  selector: 'app-login-pages',
  templateUrl: './login-pages.component.html',
  styleUrls: ['./login-pages.component.css']
})
export class LoginPagesComponent implements OnInit {
  formLogin: FormGroup = new FormGroup({})
  errorSession: boolean= false
  constructor(private authService: AuthService,
     private cookie:CookieService,
    private router:Router) {}

  ngOnInit(): void {
    this.formLogin = new FormGroup({
      email: new FormControl('', [
        Validators.required,
      Validators.email
      ]),
      password: new FormControl('', [
        Validators.required,
        Validators.minLength(6),
        Validators.maxLength(12),
      ])
    })
  }

  sendLogin(): void {
    const { email, password } = this.formLogin.value
    this.authService.sendCredentials(email, password)
    .subscribe(responseOk => {
      console.log("sesión iniciada correctamente");
      const {tokenSession, data}= responseOk
      this.cookie.set('token', tokenSession, 4, '/')
      this.router.navigate(['/','tracks'])
    }, err => {
      this.errorSession= true
      console.log("Mail o password inválido");
    })
  }

}
