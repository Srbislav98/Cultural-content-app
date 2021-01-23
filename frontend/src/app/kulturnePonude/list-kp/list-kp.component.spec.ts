import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListKpComponent } from './list-kp.component';

describe('ListKpComponent', () => {
  let component: ListKpComponent;
  let fixture: ComponentFixture<ListKpComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListKpComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListKpComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
