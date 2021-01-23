import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TableKpComponent } from './table-kp.component';

describe('TableKpComponent', () => {
  let component: TableKpComponent;
  let fixture: ComponentFixture<TableKpComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TableKpComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TableKpComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
