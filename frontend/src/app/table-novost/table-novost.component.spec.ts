import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TableNovostComponent } from './table-novost.component';

describe('TableNovostComponent', () => {
  let component: TableNovostComponent;
  let fixture: ComponentFixture<TableNovostComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TableNovostComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TableNovostComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
