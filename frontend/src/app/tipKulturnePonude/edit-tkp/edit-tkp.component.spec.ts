import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditTkpComponent } from './edit-tkp.component';

describe('EditTkpComponent', () => {
  let component: EditTkpComponent;
  let fixture: ComponentFixture<EditTkpComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditTkpComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EditTkpComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
