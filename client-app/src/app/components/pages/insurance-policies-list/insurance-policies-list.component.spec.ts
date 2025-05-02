import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InsurancePoliciesListComponent } from './insurance-policies-list.component';

describe('InsurancePoliciesListComponent', () => {
  let component: InsurancePoliciesListComponent;
  let fixture: ComponentFixture<InsurancePoliciesListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [InsurancePoliciesListComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(InsurancePoliciesListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
