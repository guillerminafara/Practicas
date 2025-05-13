import { Component, Inject, OnInit } from '@angular/core';
import { Author } from '../model/Author';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { AuthorService } from '../author.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { spaceValidator } from 'src/app/validators/space-validator';

@Component({
  selector: 'app-author-edit',
  templateUrl: './author-edit.component.html',
  styleUrls: ['./author-edit.component.scss']
})
export class AuthorEditComponent implements OnInit {
  form: FormGroup;
  author: Author;
  constructor(public dialogRef: MatDialogRef<AuthorEditComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private authorService: AuthorService,
    private fb: FormBuilder
  ) {
  this.form = this.fb.group({
        idTF: [{ value: '', disabled: true }],
        authorTF: ['', [Validators.required, spaceValidator]],
        nationalityTF: ['', [Validators.required, spaceValidator]],
      })
  }
  ngOnInit(): void {
    if (this.data.author != null) {
      this.author = Object.assign({}, this.data.author);
    } else {
      this.author = new Author();
    }
  }

  onSave() {

    this.authorService.SaveAuthor(this.author).subscribe(result => {
      this.dialogRef.close();
    })


  }
  onClose() {
    this.dialogRef.close();
  }

}
