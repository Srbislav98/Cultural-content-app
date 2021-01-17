import { ComponentFixture, TestBed } from '@angular/core/testing';

import { KulturnaPonudaDetaljnoComponent } from './kulturna-ponuda-detaljno.component';

describe('KulturnaPonudaDetaljnoComponent', () => {
  let component: KulturnaPonudaDetaljnoComponent;
  let fixture: ComponentFixture<KulturnaPonudaDetaljnoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ KulturnaPonudaDetaljnoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(KulturnaPonudaDetaljnoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
