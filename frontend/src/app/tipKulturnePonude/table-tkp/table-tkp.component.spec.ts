import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TableTkpComponent } from './table-tkp.component';

describe('TableTkpComponent', () => {
  let component: TableTkpComponent;
  let fixture: ComponentFixture<TableTkpComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TableTkpComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TableTkpComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
