import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FavoritesPagesComponent } from './favorites-pages.component';

describe('FavoritesPagesComponent', () => {
  let component: FavoritesPagesComponent;
  let fixture: ComponentFixture<FavoritesPagesComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FavoritesPagesComponent]
    });
    fixture = TestBed.createComponent(FavoritesPagesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
