import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListproduitComponent } from './listproduit.component';

describe('ListproduitComponent', () => {
  let component: ListproduitComponent;
  let fixture: ComponentFixture<ListproduitComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListproduitComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListproduitComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
