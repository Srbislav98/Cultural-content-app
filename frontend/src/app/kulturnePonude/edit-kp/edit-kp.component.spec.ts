import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditKpComponent } from './edit-kp.component';

describe('EditKpComponent', () => {
  let component: EditKpComponent;
  let fixture: ComponentFixture<EditKpComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditKpComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EditKpComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
