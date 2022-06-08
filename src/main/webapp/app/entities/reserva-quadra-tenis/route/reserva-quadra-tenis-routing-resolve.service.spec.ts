import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, ActivatedRoute, Router, convertToParamMap } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { IReservaQuadraTenis, ReservaQuadraTenis } from '../reserva-quadra-tenis.model';
import { ReservaQuadraTenisService } from '../service/reserva-quadra-tenis.service';

import { ReservaQuadraTenisRoutingResolveService } from './reserva-quadra-tenis-routing-resolve.service';

describe('ReservaQuadraTenis routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let routingResolveService: ReservaQuadraTenisRoutingResolveService;
  let service: ReservaQuadraTenisService;
  let resultReservaQuadraTenis: IReservaQuadraTenis | undefined;

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
    routingResolveService = TestBed.inject(ReservaQuadraTenisRoutingResolveService);
    service = TestBed.inject(ReservaQuadraTenisService);
    resultReservaQuadraTenis = undefined;
  });

  describe('resolve', () => {
    it('should return IReservaQuadraTenis returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultReservaQuadraTenis = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultReservaQuadraTenis).toEqual({ id: 123 });
    });

    it('should return new IReservaQuadraTenis if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultReservaQuadraTenis = result;
      });

      // THEN
      expect(service.find).not.toBeCalled();
      expect(resultReservaQuadraTenis).toEqual(new ReservaQuadraTenis());
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse({ body: null as unknown as ReservaQuadraTenis })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultReservaQuadraTenis = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultReservaQuadraTenis).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
