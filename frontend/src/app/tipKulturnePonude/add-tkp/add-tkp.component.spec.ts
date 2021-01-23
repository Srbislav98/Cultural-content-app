import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddTkpComponent } from './add-tkp.component';

describe('AddTkpComponent', () => {
  let component: AddTkpComponent;
  let fixture: ComponentFixture<AddTkpComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddTkpComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddTkpComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
