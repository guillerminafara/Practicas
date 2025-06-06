import { Component, Inject, OnInit } from '@angular/core';
import { Game } from '../model/Game';
import { Category } from 'src/app/category/model/Category';
import { Author } from 'src/app/author/model/Author';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { CategoryService } from 'src/app/category/category.service';
import { AuthorService } from 'src/app/author/author.service';
import { GameService } from '../game.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { spaceValidator } from 'src/app/validators/space-validator';
import { positiveIntegerValidator } from 'src/app/validators/positive-integer-validator';

@Component({
  selector: 'app-game-edit',
  templateUrl: './game-edit.component.html',
  styleUrls: ['./game-edit.component.scss']
})
export class GameEditComponent implements OnInit {
  game: Game;
  authors: Author[];
  categories: Category[];
  form: FormGroup;

  constructor(
    public dialogRef: MatDialogRef<GameEditComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private gameService: GameService,
    private categoryService: CategoryService,
    private authorService: AuthorService,
    private fb: FormBuilder
  ) {
    this.form = this.fb.group({
      id: [{ value: '', disabled: true }],
      titleGame: ['', [Validators.required, spaceValidator]],
      agaRange: ['', [Validators.required, positiveIntegerValidator]],
      category: ['', [Validators.required]],
      author: ['', [Validators.required]]
    })
  }

  ngOnInit(): void {
    if (this.data.game != null) {
      this.game = Object.assign({}, this.data.game);
    } else {
      this.game = new Game();
    }
    this.categoryService.getCategories().subscribe(
      categories => {
        this.categories = categories;

        if (this.game.category != null) {
          const categoryFilter: Category[] = categories.filter(category => category.id == this.data.game.category.id);
          if (categoryFilter != null) {
            this.game.category = categoryFilter[0];
          }
        }
      }
    );
    this.authorService.getAllAuthors().subscribe(
      authors => {
        this.authors = authors

        if (this.game.author != null) {
          const authorFilter: Author[] = authors.filter(author => author.id == this.data.game.author.id);
          if (authorFilter != null) {
            this.game.author = authorFilter[0];
          }
        }
      }
    );
  }
  onSave() {
    this.gameService.saveGame(this.game).subscribe(result => {
      this.dialogRef.close();
    });
  }

  onClose() {
    this.dialogRef.close();
  }
}
