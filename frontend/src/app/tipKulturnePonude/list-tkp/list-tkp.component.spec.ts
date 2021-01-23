import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListTkpComponent } from './list-tkp.component';

describe('ListTkpComponent', () => {
  let component: ListTkpComponent;
  let fixture: ComponentFixture<ListTkpComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListTkpComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListTkpComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
