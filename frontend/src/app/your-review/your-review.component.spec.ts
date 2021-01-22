import { ComponentFixture, TestBed } from '@angular/core/testing';

import { YourReviewComponent } from './your-review.component';

describe('YourReviewComponent', () => {
  let component: YourReviewComponent;
  let fixture: ComponentFixture<YourReviewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ YourReviewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(YourReviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
