import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PeliculasPosterGridComponent } from './peliculas-poster-grid.component';

describe('PeliculasPosterGridComponent', () => {
  let component: PeliculasPosterGridComponent;
  let fixture: ComponentFixture<PeliculasPosterGridComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PeliculasPosterGridComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PeliculasPosterGridComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
