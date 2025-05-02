import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BuyInsurancePolicyComponent } from './buy-insurance-policy.component';

describe('BuyInsurancePolicyComponent', () => {
  let component: BuyInsurancePolicyComponent;
  let fixture: ComponentFixture<BuyInsurancePolicyComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BuyInsurancePolicyComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(BuyInsurancePolicyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
