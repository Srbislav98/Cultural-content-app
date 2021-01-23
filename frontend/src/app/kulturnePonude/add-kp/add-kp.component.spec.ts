import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddKpComponent } from './add-kp.component';

describe('AddKpComponent', () => {
  let component: AddKpComponent;
  let fixture: ComponentFixture<AddKpComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddKpComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddKpComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
