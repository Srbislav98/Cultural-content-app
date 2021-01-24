import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TableRecenzijaComponent } from './table-recenzija.component';

describe('TableRecenzijaComponent', () => {
  let component: TableRecenzijaComponent;
  let fixture: ComponentFixture<TableRecenzijaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TableRecenzijaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TableRecenzijaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
