import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, ActivatedRoute, Router, convertToParamMap } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { ICronogramaDiario, CronogramaDiario } from '../cronograma-diario.model';
import { CronogramaDiarioService } from '../service/cronograma-diario.service';

import { CronogramaDiarioRoutingResolveService } from './cronograma-diario-routing-resolve.service';

describe('CronogramaDiario routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let routingResolveService: CronogramaDiarioRoutingResolveService;
  let service: CronogramaDiarioService;
  let resultCronogramaDiario: ICronogramaDiario | undefined;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: {
            snapshot: {
              paramMap: convertToParamMap({}),
            },
          },
        },
      ],
    });
    mockRouter = TestBed.inject(Router);
    jest.spyOn(mockRouter, 'navigate').mockImplementation(() => Promise.resolve(true));
    mockActivatedRouteSnapshot = TestBed.inject(ActivatedRoute).snapshot;
    routingResolveService = TestBed.inject(CronogramaDiarioRoutingResolveService);
    service = TestBed.inject(CronogramaDiarioService);
    resultCronogramaDiario = undefined;
  });

  describe('resolve', () => {
    it('should return ICronogramaDiario returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultCronogramaDiario = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultCronogramaDiario).toEqual({ id: 123 });
    });

    it('should return new ICronogramaDiario if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultCronogramaDiario = result;
      });

      // THEN
      expect(service.find).not.toBeCalled();
      expect(resultCronogramaDiario).toEqual(new CronogramaDiario());
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse({ body: null as unknown as CronogramaDiario })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultCronogramaDiario = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultCronogramaDiario).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
