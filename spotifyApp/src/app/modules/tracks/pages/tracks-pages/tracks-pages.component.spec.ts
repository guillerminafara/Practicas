import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TracksPagesComponent } from './tracks-pages.component';

describe('TracksPagesComponent', () => {
  let component: TracksPagesComponent;
  let fixture: ComponentFixture<TracksPagesComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [TracksPagesComponent]
    });
    fixture = TestBed.createComponent(TracksPagesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
