import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IBlocos, getBlocosIdentifier } from '../blocos.model';
export type EntityResponseType = HttpResponse<IBlocos>;
export type EntityArrayResponseType = HttpResponse<IBlocos[]>;

@Injectable({ providedIn: 'root' })
export class BlocosService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/blocos');
  protected resourceUrlByDate = this.applicationConfigService.getEndpointFor('api/blocos/by-date');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(blocos: IBlocos): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(blocos);
    return this.http
      .post<IBlocos>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(blocos: IBlocos): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(blocos);
    return this.http
      .put<IBlocos>(`${this.resourceUrl}/${getBlocosIdentifier(blocos) as number}`, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  partialUpdate(blocos: IBlocos): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(blocos);
    return this.http
      .patch<IBlocos>(`${this.resourceUrl}/${getBlocosIdentifier(blocos) as number}`, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IBlocos>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  findByDate(date: string): Observable<EntityArrayResponseType> {
    return this.http
      .get<IBlocos[]>(`${this.resourceUrlByDate}/${date}`, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IBlocos[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addBlocosToCollectionIfMissing(blocosCollection: IBlocos[], ...blocosToCheck: (IBlocos | null | undefined)[]): IBlocos[] {
    const blocos: IBlocos[] = blocosToCheck.filter(isPresent);
    if (blocos.length > 0) {
      const blocosCollectionIdentifiers = blocosCollection.map(blocosItem => getBlocosIdentifier(blocosItem)!);
      const blocosToAdd = blocos.filter(blocosItem => {
        const blocosIdentifier = getBlocosIdentifier(blocosItem);
        if (blocosIdentifier == null || blocosCollectionIdentifiers.includes(blocosIdentifier)) {
          return false;
        }
        blocosCollectionIdentifiers.push(blocosIdentifier);
        return true;
      });
      return [...blocosToAdd, ...blocosCollection];
    }
    return blocosCollection;
  }

  protected convertDateFromClient(blocos: IBlocos): IBlocos {
    return Object.assign({}, blocos, {
      zeroAM: blocos.zeroAM?.isValid() ? blocos.zeroAM.toJSON() : undefined,
      zeroAMeDez: blocos.zeroAMeDez?.isValid() ? blocos.zeroAMeDez.toJSON() : undefined,
      zeroAMeVinte: blocos.zeroAMeVinte?.isValid() ? blocos.zeroAMeVinte.toJSON() : undefined,
      zeroAMeTrinta: blocos.zeroAMeTrinta?.isValid() ? blocos.zeroAMeTrinta.toJSON() : undefined,
      zeroAMeQuarenta: blocos.zeroAMeQuarenta?.isValid() ? blocos.zeroAMeQuarenta.toJSON() : undefined,
      zeroAMeCinquenta: blocos.zeroAMeCinquenta?.isValid() ? blocos.zeroAMeCinquenta.toJSON() : undefined,
      zeroPM: blocos.zeroPM?.isValid() ? blocos.zeroPM.toJSON() : undefined,
      zeroPMeDez: blocos.zeroPMeDez?.isValid() ? blocos.zeroPMeDez.toJSON() : undefined,
      zeroPMeVinte: blocos.zeroPMeVinte?.isValid() ? blocos.zeroPMeVinte.toJSON() : undefined,
      zeroPMeTrinta: blocos.zeroPMeTrinta?.isValid() ? blocos.zeroPMeTrinta.toJSON() : undefined,
      zeroPMeQuarenta: blocos.zeroPMeQuarenta?.isValid() ? blocos.zeroPMeQuarenta.toJSON() : undefined,
      zeroPMeCinquenta: blocos.zeroPMeCinquenta?.isValid() ? blocos.zeroPMeCinquenta.toJSON() : undefined,
      umAM: blocos.umAM?.isValid() ? blocos.umAM.toJSON() : undefined,
      umAMeDez: blocos.umAMeDez?.isValid() ? blocos.umAMeDez.toJSON() : undefined,
      umAMeVinte: blocos.umAMeVinte?.isValid() ? blocos.umAMeVinte.toJSON() : undefined,
      umAMeTrinta: blocos.umAMeTrinta?.isValid() ? blocos.umAMeTrinta.toJSON() : undefined,
      umAMeQuarenta: blocos.umAMeQuarenta?.isValid() ? blocos.umAMeQuarenta.toJSON() : undefined,
      umAMeCinquenta: blocos.umAMeCinquenta?.isValid() ? blocos.umAMeCinquenta.toJSON() : undefined,
      umPM: blocos.umPM?.isValid() ? blocos.umPM.toJSON() : undefined,
      umPMeDez: blocos.umPMeDez?.isValid() ? blocos.umPMeDez.toJSON() : undefined,
      umPMeVinte: blocos.umPMeVinte?.isValid() ? blocos.umPMeVinte.toJSON() : undefined,
      umPMeTrinta: blocos.umPMeTrinta?.isValid() ? blocos.umPMeTrinta.toJSON() : undefined,
      umPMeQuarenta: blocos.umPMeQuarenta?.isValid() ? blocos.umPMeQuarenta.toJSON() : undefined,
      umPMeCinquenta: blocos.umPMeCinquenta?.isValid() ? blocos.umPMeCinquenta.toJSON() : undefined,
      doisAM: blocos.doisAM?.isValid() ? blocos.doisAM.toJSON() : undefined,
      doisAMeDez: blocos.doisAMeDez?.isValid() ? blocos.doisAMeDez.toJSON() : undefined,
      doisAMeVinte: blocos.doisAMeVinte?.isValid() ? blocos.doisAMeVinte.toJSON() : undefined,
      doisAMeTrinta: blocos.doisAMeTrinta?.isValid() ? blocos.doisAMeTrinta.toJSON() : undefined,
      doisAMeQuarenta: blocos.doisAMeQuarenta?.isValid() ? blocos.doisAMeQuarenta.toJSON() : undefined,
      doisAMeCinquenta: blocos.doisAMeCinquenta?.isValid() ? blocos.doisAMeCinquenta.toJSON() : undefined,
      doisPM: blocos.doisPM?.isValid() ? blocos.doisPM.toJSON() : undefined,
      doisPMeDez: blocos.doisPMeDez?.isValid() ? blocos.doisPMeDez.toJSON() : undefined,
      doisPMeVinte: blocos.doisPMeVinte?.isValid() ? blocos.doisPMeVinte.toJSON() : undefined,
      doisPMeTrinta: blocos.doisPMeTrinta?.isValid() ? blocos.doisPMeTrinta.toJSON() : undefined,
      doisPMeQuarenta: blocos.doisPMeQuarenta?.isValid() ? blocos.doisPMeQuarenta.toJSON() : undefined,
      doisPMeCinquenta: blocos.doisPMeCinquenta?.isValid() ? blocos.doisPMeCinquenta.toJSON() : undefined,
      tresAM: blocos.tresAM?.isValid() ? blocos.tresAM.toJSON() : undefined,
      tresAMeDez: blocos.tresAMeDez?.isValid() ? blocos.tresAMeDez.toJSON() : undefined,
      tresAMeVinte: blocos.tresAMeVinte?.isValid() ? blocos.tresAMeVinte.toJSON() : undefined,
      tresAMeTrinta: blocos.tresAMeTrinta?.isValid() ? blocos.tresAMeTrinta.toJSON() : undefined,
      tresAMeQuarenta: blocos.tresAMeQuarenta?.isValid() ? blocos.tresAMeQuarenta.toJSON() : undefined,
      tresAMeCinquenta: blocos.tresAMeCinquenta?.isValid() ? blocos.tresAMeCinquenta.toJSON() : undefined,
      tresPM: blocos.tresPM?.isValid() ? blocos.tresPM.toJSON() : undefined,
      tresPMeDez: blocos.tresPMeDez?.isValid() ? blocos.tresPMeDez.toJSON() : undefined,
      tresPMeVinte: blocos.tresPMeVinte?.isValid() ? blocos.tresPMeVinte.toJSON() : undefined,
      tresPMeTrinta: blocos.tresPMeTrinta?.isValid() ? blocos.tresPMeTrinta.toJSON() : undefined,
      tresPMeQuarenta: blocos.tresPMeQuarenta?.isValid() ? blocos.tresPMeQuarenta.toJSON() : undefined,
      tresPMeCinquenta: blocos.tresPMeCinquenta?.isValid() ? blocos.tresPMeCinquenta.toJSON() : undefined,
      quatroAM: blocos.quatroAM?.isValid() ? blocos.quatroAM.toJSON() : undefined,
      quatroAMeDez: blocos.quatroAMeDez?.isValid() ? blocos.quatroAMeDez.toJSON() : undefined,
      quatroAMeVinte: blocos.quatroAMeVinte?.isValid() ? blocos.quatroAMeVinte.toJSON() : undefined,
      quatroAMeTrinta: blocos.quatroAMeTrinta?.isValid() ? blocos.quatroAMeTrinta.toJSON() : undefined,
      quatroAMeQuarenta: blocos.quatroAMeQuarenta?.isValid() ? blocos.quatroAMeQuarenta.toJSON() : undefined,
      quatroAMeCinquenta: blocos.quatroAMeCinquenta?.isValid() ? blocos.quatroAMeCinquenta.toJSON() : undefined,
      quatroPM: blocos.quatroPM?.isValid() ? blocos.quatroPM.toJSON() : undefined,
      quatroPMeDez: blocos.quatroPMeDez?.isValid() ? blocos.quatroPMeDez.toJSON() : undefined,
      quatroPMeVinte: blocos.quatroPMeVinte?.isValid() ? blocos.quatroPMeVinte.toJSON() : undefined,
      quatroPMeTrinta: blocos.quatroPMeTrinta?.isValid() ? blocos.quatroPMeTrinta.toJSON() : undefined,
      quatroPMeQuarenta: blocos.quatroPMeQuarenta?.isValid() ? blocos.quatroPMeQuarenta.toJSON() : undefined,
      quatroPMeCinquenta: blocos.quatroPMeCinquenta?.isValid() ? blocos.quatroPMeCinquenta.toJSON() : undefined,
      cincoAM: blocos.cincoAM?.isValid() ? blocos.cincoAM.toJSON() : undefined,
      cincoAMeDez: blocos.cincoAMeDez?.isValid() ? blocos.cincoAMeDez.toJSON() : undefined,
      cincoAMeVinte: blocos.cincoAMeVinte?.isValid() ? blocos.cincoAMeVinte.toJSON() : undefined,
      cincoAMeTrinta: blocos.cincoAMeTrinta?.isValid() ? blocos.cincoAMeTrinta.toJSON() : undefined,
      cincoAMeQuarenta: blocos.cincoAMeQuarenta?.isValid() ? blocos.cincoAMeQuarenta.toJSON() : undefined,
      cincoAMeCinquenta: blocos.cincoAMeCinquenta?.isValid() ? blocos.cincoAMeCinquenta.toJSON() : undefined,
      cincoPM: blocos.cincoPM?.isValid() ? blocos.cincoPM.toJSON() : undefined,
      cincoPMeDez: blocos.cincoPMeDez?.isValid() ? blocos.cincoPMeDez.toJSON() : undefined,
      cincoPMeVinte: blocos.cincoPMeVinte?.isValid() ? blocos.cincoPMeVinte.toJSON() : undefined,
      cincoPMeTrinta: blocos.cincoPMeTrinta?.isValid() ? blocos.cincoPMeTrinta.toJSON() : undefined,
      cincoPMeQuarenta: blocos.cincoPMeQuarenta?.isValid() ? blocos.cincoPMeQuarenta.toJSON() : undefined,
      cincoPMeCinquenta: blocos.cincoPMeCinquenta?.isValid() ? blocos.cincoPMeCinquenta.toJSON() : undefined,
      seisAM: blocos.seisAM?.isValid() ? blocos.seisAM.toJSON() : undefined,
      seisAMeDez: blocos.seisAMeDez?.isValid() ? blocos.seisAMeDez.toJSON() : undefined,
      seisAMeVinte: blocos.seisAMeVinte?.isValid() ? blocos.seisAMeVinte.toJSON() : undefined,
      seisAMeTrinta: blocos.seisAMeTrinta?.isValid() ? blocos.seisAMeTrinta.toJSON() : undefined,
      seisAMeQuarenta: blocos.seisAMeQuarenta?.isValid() ? blocos.seisAMeQuarenta.toJSON() : undefined,
      seisAMeCinquenta: blocos.seisAMeCinquenta?.isValid() ? blocos.seisAMeCinquenta.toJSON() : undefined,
      seisPM: blocos.seisPM?.isValid() ? blocos.seisPM.toJSON() : undefined,
      seisPMeDez: blocos.seisPMeDez?.isValid() ? blocos.seisPMeDez.toJSON() : undefined,
      seisPMeVinte: blocos.seisPMeVinte?.isValid() ? blocos.seisPMeVinte.toJSON() : undefined,
      seisPMeTrinta: blocos.seisPMeTrinta?.isValid() ? blocos.seisPMeTrinta.toJSON() : undefined,
      seisPMeQuarenta: blocos.seisPMeQuarenta?.isValid() ? blocos.seisPMeQuarenta.toJSON() : undefined,
      seisPMeCinquenta: blocos.seisPMeCinquenta?.isValid() ? blocos.seisPMeCinquenta.toJSON() : undefined,
      seteAM: blocos.seteAM?.isValid() ? blocos.seteAM.toJSON() : undefined,
      seteAMeDez: blocos.seteAMeDez?.isValid() ? blocos.seteAMeDez.toJSON() : undefined,
      seteAMeVinte: blocos.seteAMeVinte?.isValid() ? blocos.seteAMeVinte.toJSON() : undefined,
      seteAMeTrinta: blocos.seteAMeTrinta?.isValid() ? blocos.seteAMeTrinta.toJSON() : undefined,
      seteAMeQuarenta: blocos.seteAMeQuarenta?.isValid() ? blocos.seteAMeQuarenta.toJSON() : undefined,
      seteAMeCinquenta: blocos.seteAMeCinquenta?.isValid() ? blocos.seteAMeCinquenta.toJSON() : undefined,
      setePM: blocos.setePM?.isValid() ? blocos.setePM.toJSON() : undefined,
      setePMeDez: blocos.setePMeDez?.isValid() ? blocos.setePMeDez.toJSON() : undefined,
      setePMeVinte: blocos.setePMeVinte?.isValid() ? blocos.setePMeVinte.toJSON() : undefined,
      setePMeTrinta: blocos.setePMeTrinta?.isValid() ? blocos.setePMeTrinta.toJSON() : undefined,
      setePMeQuarenta: blocos.setePMeQuarenta?.isValid() ? blocos.setePMeQuarenta.toJSON() : undefined,
      setePMeCinquenta: blocos.setePMeCinquenta?.isValid() ? blocos.setePMeCinquenta.toJSON() : undefined,
      oitoAM: blocos.oitoAM?.isValid() ? blocos.oitoAM.toJSON() : undefined,
      oitoAMeDez: blocos.oitoAMeDez?.isValid() ? blocos.oitoAMeDez.toJSON() : undefined,
      oitoAMeVinte: blocos.oitoAMeVinte?.isValid() ? blocos.oitoAMeVinte.toJSON() : undefined,
      oitoAMeTrinta: blocos.oitoAMeTrinta?.isValid() ? blocos.oitoAMeTrinta.toJSON() : undefined,
      oitoAMeQuarenta: blocos.oitoAMeQuarenta?.isValid() ? blocos.oitoAMeQuarenta.toJSON() : undefined,
      oitoAMeCinquenta: blocos.oitoAMeCinquenta?.isValid() ? blocos.oitoAMeCinquenta.toJSON() : undefined,
      oitoPM: blocos.oitoPM?.isValid() ? blocos.oitoPM.toJSON() : undefined,
      oitoPMeDez: blocos.oitoPMeDez?.isValid() ? blocos.oitoPMeDez.toJSON() : undefined,
      oitoPMeVinte: blocos.oitoPMeVinte?.isValid() ? blocos.oitoPMeVinte.toJSON() : undefined,
      oitoPMeTrinta: blocos.oitoPMeTrinta?.isValid() ? blocos.oitoPMeTrinta.toJSON() : undefined,
      oitoPMeQuarenta: blocos.oitoPMeQuarenta?.isValid() ? blocos.oitoPMeQuarenta.toJSON() : undefined,
      oitoPMeCinquenta: blocos.oitoPMeCinquenta?.isValid() ? blocos.oitoPMeCinquenta.toJSON() : undefined,
      noveAM: blocos.noveAM?.isValid() ? blocos.noveAM.toJSON() : undefined,
      noveAMeDez: blocos.noveAMeDez?.isValid() ? blocos.noveAMeDez.toJSON() : undefined,
      noveAMeVinte: blocos.noveAMeVinte?.isValid() ? blocos.noveAMeVinte.toJSON() : undefined,
      noveAMeTrinta: blocos.noveAMeTrinta?.isValid() ? blocos.noveAMeTrinta.toJSON() : undefined,
      noveAMeQuarenta: blocos.noveAMeQuarenta?.isValid() ? blocos.noveAMeQuarenta.toJSON() : undefined,
      noveAMeCinquenta: blocos.noveAMeCinquenta?.isValid() ? blocos.noveAMeCinquenta.toJSON() : undefined,
      novePM: blocos.novePM?.isValid() ? blocos.novePM.toJSON() : undefined,
      novePMeDez: blocos.novePMeDez?.isValid() ? blocos.novePMeDez.toJSON() : undefined,
      novePMeVinte: blocos.novePMeVinte?.isValid() ? blocos.novePMeVinte.toJSON() : undefined,
      novePMeTrinta: blocos.novePMeTrinta?.isValid() ? blocos.novePMeTrinta.toJSON() : undefined,
      novePMeQuarenta: blocos.novePMeQuarenta?.isValid() ? blocos.novePMeQuarenta.toJSON() : undefined,
      novePMeCinquenta: blocos.novePMeCinquenta?.isValid() ? blocos.novePMeCinquenta.toJSON() : undefined,
      dezAM: blocos.dezAM?.isValid() ? blocos.dezAM.toJSON() : undefined,
      dezAMeDez: blocos.dezAMeDez?.isValid() ? blocos.dezAMeDez.toJSON() : undefined,
      dezAMeVinte: blocos.dezAMeVinte?.isValid() ? blocos.dezAMeVinte.toJSON() : undefined,
      dezAMeTrinta: blocos.dezAMeTrinta?.isValid() ? blocos.dezAMeTrinta.toJSON() : undefined,
      dezAMeQuarenta: blocos.dezAMeQuarenta?.isValid() ? blocos.dezAMeQuarenta.toJSON() : undefined,
      dezAMeCinquenta: blocos.dezAMeCinquenta?.isValid() ? blocos.dezAMeCinquenta.toJSON() : undefined,
      dezPM: blocos.dezPM?.isValid() ? blocos.dezPM.toJSON() : undefined,
      dezPMeDez: blocos.dezPMeDez?.isValid() ? blocos.dezPMeDez.toJSON() : undefined,
      dezPMeVinte: blocos.dezPMeVinte?.isValid() ? blocos.dezPMeVinte.toJSON() : undefined,
      dezPMeTrinta: blocos.dezPMeTrinta?.isValid() ? blocos.dezPMeTrinta.toJSON() : undefined,
      dezPMeQuarenta: blocos.dezPMeQuarenta?.isValid() ? blocos.dezPMeQuarenta.toJSON() : undefined,
      dezPMeCinquenta: blocos.dezPMeCinquenta?.isValid() ? blocos.dezPMeCinquenta.toJSON() : undefined,
      onzeAM: blocos.onzeAM?.isValid() ? blocos.onzeAM.toJSON() : undefined,
      onzeAMeDez: blocos.onzeAMeDez?.isValid() ? blocos.onzeAMeDez.toJSON() : undefined,
      onzeAMeVinte: blocos.onzeAMeVinte?.isValid() ? blocos.onzeAMeVinte.toJSON() : undefined,
      onzeAMeTrinta: blocos.onzeAMeTrinta?.isValid() ? blocos.onzeAMeTrinta.toJSON() : undefined,
      onzeAMeQuarenta: blocos.onzeAMeQuarenta?.isValid() ? blocos.onzeAMeQuarenta.toJSON() : undefined,
      onzeAMeCinquenta: blocos.onzeAMeCinquenta?.isValid() ? blocos.onzeAMeCinquenta.toJSON() : undefined,
      onzePM: blocos.onzePM?.isValid() ? blocos.onzePM.toJSON() : undefined,
      onzePMeDez: blocos.onzePMeDez?.isValid() ? blocos.onzePMeDez.toJSON() : undefined,
      onzePMeVinte: blocos.onzePMeVinte?.isValid() ? blocos.onzePMeVinte.toJSON() : undefined,
      onzePMeTrinta: blocos.onzePMeTrinta?.isValid() ? blocos.onzePMeTrinta.toJSON() : undefined,
      onzePMeQuarenta: blocos.onzePMeQuarenta?.isValid() ? blocos.onzePMeQuarenta.toJSON() : undefined,
      onzePMeCinquenta: blocos.onzePMeCinquenta?.isValid() ? blocos.onzePMeCinquenta.toJSON() : undefined,
    });
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.zeroAM = res.body.zeroAM ? dayjs(res.body.zeroAM) : undefined;
      res.body.zeroAMeDez = res.body.zeroAMeDez ? dayjs(res.body.zeroAMeDez) : undefined;
      res.body.zeroAMeVinte = res.body.zeroAMeVinte ? dayjs(res.body.zeroAMeVinte) : undefined;
      res.body.zeroAMeTrinta = res.body.zeroAMeTrinta ? dayjs(res.body.zeroAMeTrinta) : undefined;
      res.body.zeroAMeQuarenta = res.body.zeroAMeQuarenta ? dayjs(res.body.zeroAMeQuarenta) : undefined;
      res.body.zeroAMeCinquenta = res.body.zeroAMeCinquenta ? dayjs(res.body.zeroAMeCinquenta) : undefined;
      res.body.zeroPM = res.body.zeroPM ? dayjs(res.body.zeroPM) : undefined;
      res.body.zeroPMeDez = res.body.zeroPMeDez ? dayjs(res.body.zeroPMeDez) : undefined;
      res.body.zeroPMeVinte = res.body.zeroPMeVinte ? dayjs(res.body.zeroPMeVinte) : undefined;
      res.body.zeroPMeTrinta = res.body.zeroPMeTrinta ? dayjs(res.body.zeroPMeTrinta) : undefined;
      res.body.zeroPMeQuarenta = res.body.zeroPMeQuarenta ? dayjs(res.body.zeroPMeQuarenta) : undefined;
      res.body.zeroPMeCinquenta = res.body.zeroPMeCinquenta ? dayjs(res.body.zeroPMeCinquenta) : undefined;
      res.body.umAM = res.body.umAM ? dayjs(res.body.umAM) : undefined;
      res.body.umAMeDez = res.body.umAMeDez ? dayjs(res.body.umAMeDez) : undefined;
      res.body.umAMeVinte = res.body.umAMeVinte ? dayjs(res.body.umAMeVinte) : undefined;
      res.body.umAMeTrinta = res.body.umAMeTrinta ? dayjs(res.body.umAMeTrinta) : undefined;
      res.body.umAMeQuarenta = res.body.umAMeQuarenta ? dayjs(res.body.umAMeQuarenta) : undefined;
      res.body.umAMeCinquenta = res.body.umAMeCinquenta ? dayjs(res.body.umAMeCinquenta) : undefined;
      res.body.umPM = res.body.umPM ? dayjs(res.body.umPM) : undefined;
      res.body.umPMeDez = res.body.umPMeDez ? dayjs(res.body.umPMeDez) : undefined;
      res.body.umPMeVinte = res.body.umPMeVinte ? dayjs(res.body.umPMeVinte) : undefined;
      res.body.umPMeTrinta = res.body.umPMeTrinta ? dayjs(res.body.umPMeTrinta) : undefined;
      res.body.umPMeQuarenta = res.body.umPMeQuarenta ? dayjs(res.body.umPMeQuarenta) : undefined;
      res.body.umPMeCinquenta = res.body.umPMeCinquenta ? dayjs(res.body.umPMeCinquenta) : undefined;
      res.body.doisAM = res.body.doisAM ? dayjs(res.body.doisAM) : undefined;
      res.body.doisAMeDez = res.body.doisAMeDez ? dayjs(res.body.doisAMeDez) : undefined;
      res.body.doisAMeVinte = res.body.doisAMeVinte ? dayjs(res.body.doisAMeVinte) : undefined;
      res.body.doisAMeTrinta = res.body.doisAMeTrinta ? dayjs(res.body.doisAMeTrinta) : undefined;
      res.body.doisAMeQuarenta = res.body.doisAMeQuarenta ? dayjs(res.body.doisAMeQuarenta) : undefined;
      res.body.doisAMeCinquenta = res.body.doisAMeCinquenta ? dayjs(res.body.doisAMeCinquenta) : undefined;
      res.body.doisPM = res.body.doisPM ? dayjs(res.body.doisPM) : undefined;
      res.body.doisPMeDez = res.body.doisPMeDez ? dayjs(res.body.doisPMeDez) : undefined;
      res.body.doisPMeVinte = res.body.doisPMeVinte ? dayjs(res.body.doisPMeVinte) : undefined;
      res.body.doisPMeTrinta = res.body.doisPMeTrinta ? dayjs(res.body.doisPMeTrinta) : undefined;
      res.body.doisPMeQuarenta = res.body.doisPMeQuarenta ? dayjs(res.body.doisPMeQuarenta) : undefined;
      res.body.doisPMeCinquenta = res.body.doisPMeCinquenta ? dayjs(res.body.doisPMeCinquenta) : undefined;
      res.body.tresAM = res.body.tresAM ? dayjs(res.body.tresAM) : undefined;
      res.body.tresAMeDez = res.body.tresAMeDez ? dayjs(res.body.tresAMeDez) : undefined;
      res.body.tresAMeVinte = res.body.tresAMeVinte ? dayjs(res.body.tresAMeVinte) : undefined;
      res.body.tresAMeTrinta = res.body.tresAMeTrinta ? dayjs(res.body.tresAMeTrinta) : undefined;
      res.body.tresAMeQuarenta = res.body.tresAMeQuarenta ? dayjs(res.body.tresAMeQuarenta) : undefined;
      res.body.tresAMeCinquenta = res.body.tresAMeCinquenta ? dayjs(res.body.tresAMeCinquenta) : undefined;
      res.body.tresPM = res.body.tresPM ? dayjs(res.body.tresPM) : undefined;
      res.body.tresPMeDez = res.body.tresPMeDez ? dayjs(res.body.tresPMeDez) : undefined;
      res.body.tresPMeVinte = res.body.tresPMeVinte ? dayjs(res.body.tresPMeVinte) : undefined;
      res.body.tresPMeTrinta = res.body.tresPMeTrinta ? dayjs(res.body.tresPMeTrinta) : undefined;
      res.body.tresPMeQuarenta = res.body.tresPMeQuarenta ? dayjs(res.body.tresPMeQuarenta) : undefined;
      res.body.tresPMeCinquenta = res.body.tresPMeCinquenta ? dayjs(res.body.tresPMeCinquenta) : undefined;
      res.body.quatroAM = res.body.quatroAM ? dayjs(res.body.quatroAM) : undefined;
      res.body.quatroAMeDez = res.body.quatroAMeDez ? dayjs(res.body.quatroAMeDez) : undefined;
      res.body.quatroAMeVinte = res.body.quatroAMeVinte ? dayjs(res.body.quatroAMeVinte) : undefined;
      res.body.quatroAMeTrinta = res.body.quatroAMeTrinta ? dayjs(res.body.quatroAMeTrinta) : undefined;
      res.body.quatroAMeQuarenta = res.body.quatroAMeQuarenta ? dayjs(res.body.quatroAMeQuarenta) : undefined;
      res.body.quatroAMeCinquenta = res.body.quatroAMeCinquenta ? dayjs(res.body.quatroAMeCinquenta) : undefined;
      res.body.quatroPM = res.body.quatroPM ? dayjs(res.body.quatroPM) : undefined;
      res.body.quatroPMeDez = res.body.quatroPMeDez ? dayjs(res.body.quatroPMeDez) : undefined;
      res.body.quatroPMeVinte = res.body.quatroPMeVinte ? dayjs(res.body.quatroPMeVinte) : undefined;
      res.body.quatroPMeTrinta = res.body.quatroPMeTrinta ? dayjs(res.body.quatroPMeTrinta) : undefined;
      res.body.quatroPMeQuarenta = res.body.quatroPMeQuarenta ? dayjs(res.body.quatroPMeQuarenta) : undefined;
      res.body.quatroPMeCinquenta = res.body.quatroPMeCinquenta ? dayjs(res.body.quatroPMeCinquenta) : undefined;
      res.body.cincoAM = res.body.cincoAM ? dayjs(res.body.cincoAM) : undefined;
      res.body.cincoAMeDez = res.body.cincoAMeDez ? dayjs(res.body.cincoAMeDez) : undefined;
      res.body.cincoAMeVinte = res.body.cincoAMeVinte ? dayjs(res.body.cincoAMeVinte) : undefined;
      res.body.cincoAMeTrinta = res.body.cincoAMeTrinta ? dayjs(res.body.cincoAMeTrinta) : undefined;
      res.body.cincoAMeQuarenta = res.body.cincoAMeQuarenta ? dayjs(res.body.cincoAMeQuarenta) : undefined;
      res.body.cincoAMeCinquenta = res.body.cincoAMeCinquenta ? dayjs(res.body.cincoAMeCinquenta) : undefined;
      res.body.cincoPM = res.body.cincoPM ? dayjs(res.body.cincoPM) : undefined;
      res.body.cincoPMeDez = res.body.cincoPMeDez ? dayjs(res.body.cincoPMeDez) : undefined;
      res.body.cincoPMeVinte = res.body.cincoPMeVinte ? dayjs(res.body.cincoPMeVinte) : undefined;
      res.body.cincoPMeTrinta = res.body.cincoPMeTrinta ? dayjs(res.body.cincoPMeTrinta) : undefined;
      res.body.cincoPMeQuarenta = res.body.cincoPMeQuarenta ? dayjs(res.body.cincoPMeQuarenta) : undefined;
      res.body.cincoPMeCinquenta = res.body.cincoPMeCinquenta ? dayjs(res.body.cincoPMeCinquenta) : undefined;
      res.body.seisAM = res.body.seisAM ? dayjs(res.body.seisAM) : undefined;
      res.body.seisAMeDez = res.body.seisAMeDez ? dayjs(res.body.seisAMeDez) : undefined;
      res.body.seisAMeVinte = res.body.seisAMeVinte ? dayjs(res.body.seisAMeVinte) : undefined;
      res.body.seisAMeTrinta = res.body.seisAMeTrinta ? dayjs(res.body.seisAMeTrinta) : undefined;
      res.body.seisAMeQuarenta = res.body.seisAMeQuarenta ? dayjs(res.body.seisAMeQuarenta) : undefined;
      res.body.seisAMeCinquenta = res.body.seisAMeCinquenta ? dayjs(res.body.seisAMeCinquenta) : undefined;
      res.body.seisPM = res.body.seisPM ? dayjs(res.body.seisPM) : undefined;
      res.body.seisPMeDez = res.body.seisPMeDez ? dayjs(res.body.seisPMeDez) : undefined;
      res.body.seisPMeVinte = res.body.seisPMeVinte ? dayjs(res.body.seisPMeVinte) : undefined;
      res.body.seisPMeTrinta = res.body.seisPMeTrinta ? dayjs(res.body.seisPMeTrinta) : undefined;
      res.body.seisPMeQuarenta = res.body.seisPMeQuarenta ? dayjs(res.body.seisPMeQuarenta) : undefined;
      res.body.seisPMeCinquenta = res.body.seisPMeCinquenta ? dayjs(res.body.seisPMeCinquenta) : undefined;
      res.body.seteAM = res.body.seteAM ? dayjs(res.body.seteAM) : undefined;
      res.body.seteAMeDez = res.body.seteAMeDez ? dayjs(res.body.seteAMeDez) : undefined;
      res.body.seteAMeVinte = res.body.seteAMeVinte ? dayjs(res.body.seteAMeVinte) : undefined;
      res.body.seteAMeTrinta = res.body.seteAMeTrinta ? dayjs(res.body.seteAMeTrinta) : undefined;
      res.body.seteAMeQuarenta = res.body.seteAMeQuarenta ? dayjs(res.body.seteAMeQuarenta) : undefined;
      res.body.seteAMeCinquenta = res.body.seteAMeCinquenta ? dayjs(res.body.seteAMeCinquenta) : undefined;
      res.body.setePM = res.body.setePM ? dayjs(res.body.setePM) : undefined;
      res.body.setePMeDez = res.body.setePMeDez ? dayjs(res.body.setePMeDez) : undefined;
      res.body.setePMeVinte = res.body.setePMeVinte ? dayjs(res.body.setePMeVinte) : undefined;
      res.body.setePMeTrinta = res.body.setePMeTrinta ? dayjs(res.body.setePMeTrinta) : undefined;
      res.body.setePMeQuarenta = res.body.setePMeQuarenta ? dayjs(res.body.setePMeQuarenta) : undefined;
      res.body.setePMeCinquenta = res.body.setePMeCinquenta ? dayjs(res.body.setePMeCinquenta) : undefined;
      res.body.oitoAM = res.body.oitoAM ? dayjs(res.body.oitoAM) : undefined;
      res.body.oitoAMeDez = res.body.oitoAMeDez ? dayjs(res.body.oitoAMeDez) : undefined;
      res.body.oitoAMeVinte = res.body.oitoAMeVinte ? dayjs(res.body.oitoAMeVinte) : undefined;
      res.body.oitoAMeTrinta = res.body.oitoAMeTrinta ? dayjs(res.body.oitoAMeTrinta) : undefined;
      res.body.oitoAMeQuarenta = res.body.oitoAMeQuarenta ? dayjs(res.body.oitoAMeQuarenta) : undefined;
      res.body.oitoAMeCinquenta = res.body.oitoAMeCinquenta ? dayjs(res.body.oitoAMeCinquenta) : undefined;
      res.body.oitoPM = res.body.oitoPM ? dayjs(res.body.oitoPM) : undefined;
      res.body.oitoPMeDez = res.body.oitoPMeDez ? dayjs(res.body.oitoPMeDez) : undefined;
      res.body.oitoPMeVinte = res.body.oitoPMeVinte ? dayjs(res.body.oitoPMeVinte) : undefined;
      res.body.oitoPMeTrinta = res.body.oitoPMeTrinta ? dayjs(res.body.oitoPMeTrinta) : undefined;
      res.body.oitoPMeQuarenta = res.body.oitoPMeQuarenta ? dayjs(res.body.oitoPMeQuarenta) : undefined;
      res.body.oitoPMeCinquenta = res.body.oitoPMeCinquenta ? dayjs(res.body.oitoPMeCinquenta) : undefined;
      res.body.noveAM = res.body.noveAM ? dayjs(res.body.noveAM) : undefined;
      res.body.noveAMeDez = res.body.noveAMeDez ? dayjs(res.body.noveAMeDez) : undefined;
      res.body.noveAMeVinte = res.body.noveAMeVinte ? dayjs(res.body.noveAMeVinte) : undefined;
      res.body.noveAMeTrinta = res.body.noveAMeTrinta ? dayjs(res.body.noveAMeTrinta) : undefined;
      res.body.noveAMeQuarenta = res.body.noveAMeQuarenta ? dayjs(res.body.noveAMeQuarenta) : undefined;
      res.body.noveAMeCinquenta = res.body.noveAMeCinquenta ? dayjs(res.body.noveAMeCinquenta) : undefined;
      res.body.novePM = res.body.novePM ? dayjs(res.body.novePM) : undefined;
      res.body.novePMeDez = res.body.novePMeDez ? dayjs(res.body.novePMeDez) : undefined;
      res.body.novePMeVinte = res.body.novePMeVinte ? dayjs(res.body.novePMeVinte) : undefined;
      res.body.novePMeTrinta = res.body.novePMeTrinta ? dayjs(res.body.novePMeTrinta) : undefined;
      res.body.novePMeQuarenta = res.body.novePMeQuarenta ? dayjs(res.body.novePMeQuarenta) : undefined;
      res.body.novePMeCinquenta = res.body.novePMeCinquenta ? dayjs(res.body.novePMeCinquenta) : undefined;
      res.body.dezAM = res.body.dezAM ? dayjs(res.body.dezAM) : undefined;
      res.body.dezAMeDez = res.body.dezAMeDez ? dayjs(res.body.dezAMeDez) : undefined;
      res.body.dezAMeVinte = res.body.dezAMeVinte ? dayjs(res.body.dezAMeVinte) : undefined;
      res.body.dezAMeTrinta = res.body.dezAMeTrinta ? dayjs(res.body.dezAMeTrinta) : undefined;
      res.body.dezAMeQuarenta = res.body.dezAMeQuarenta ? dayjs(res.body.dezAMeQuarenta) : undefined;
      res.body.dezAMeCinquenta = res.body.dezAMeCinquenta ? dayjs(res.body.dezAMeCinquenta) : undefined;
      res.body.dezPM = res.body.dezPM ? dayjs(res.body.dezPM) : undefined;
      res.body.dezPMeDez = res.body.dezPMeDez ? dayjs(res.body.dezPMeDez) : undefined;
      res.body.dezPMeVinte = res.body.dezPMeVinte ? dayjs(res.body.dezPMeVinte) : undefined;
      res.body.dezPMeTrinta = res.body.dezPMeTrinta ? dayjs(res.body.dezPMeTrinta) : undefined;
      res.body.dezPMeQuarenta = res.body.dezPMeQuarenta ? dayjs(res.body.dezPMeQuarenta) : undefined;
      res.body.dezPMeCinquenta = res.body.dezPMeCinquenta ? dayjs(res.body.dezPMeCinquenta) : undefined;
      res.body.onzeAM = res.body.onzeAM ? dayjs(res.body.onzeAM) : undefined;
      res.body.onzeAMeDez = res.body.onzeAMeDez ? dayjs(res.body.onzeAMeDez) : undefined;
      res.body.onzeAMeVinte = res.body.onzeAMeVinte ? dayjs(res.body.onzeAMeVinte) : undefined;
      res.body.onzeAMeTrinta = res.body.onzeAMeTrinta ? dayjs(res.body.onzeAMeTrinta) : undefined;
      res.body.onzeAMeQuarenta = res.body.onzeAMeQuarenta ? dayjs(res.body.onzeAMeQuarenta) : undefined;
      res.body.onzeAMeCinquenta = res.body.onzeAMeCinquenta ? dayjs(res.body.onzeAMeCinquenta) : undefined;
      res.body.onzePM = res.body.onzePM ? dayjs(res.body.onzePM) : undefined;
      res.body.onzePMeDez = res.body.onzePMeDez ? dayjs(res.body.onzePMeDez) : undefined;
      res.body.onzePMeVinte = res.body.onzePMeVinte ? dayjs(res.body.onzePMeVinte) : undefined;
      res.body.onzePMeTrinta = res.body.onzePMeTrinta ? dayjs(res.body.onzePMeTrinta) : undefined;
      res.body.onzePMeQuarenta = res.body.onzePMeQuarenta ? dayjs(res.body.onzePMeQuarenta) : undefined;
      res.body.onzePMeCinquenta = res.body.onzePMeCinquenta ? dayjs(res.body.onzePMeCinquenta) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((blocos: IBlocos) => {
        blocos.zeroAM = blocos.zeroAM ? dayjs(blocos.zeroAM) : undefined;
        blocos.zeroAMeDez = blocos.zeroAMeDez ? dayjs(blocos.zeroAMeDez) : undefined;
        blocos.zeroAMeVinte = blocos.zeroAMeVinte ? dayjs(blocos.zeroAMeVinte) : undefined;
        blocos.zeroAMeTrinta = blocos.zeroAMeTrinta ? dayjs(blocos.zeroAMeTrinta) : undefined;
        blocos.zeroAMeQuarenta = blocos.zeroAMeQuarenta ? dayjs(blocos.zeroAMeQuarenta) : undefined;
        blocos.zeroAMeCinquenta = blocos.zeroAMeCinquenta ? dayjs(blocos.zeroAMeCinquenta) : undefined;
        blocos.zeroPM = blocos.zeroPM ? dayjs(blocos.zeroPM) : undefined;
        blocos.zeroPMeDez = blocos.zeroPMeDez ? dayjs(blocos.zeroPMeDez) : undefined;
        blocos.zeroPMeVinte = blocos.zeroPMeVinte ? dayjs(blocos.zeroPMeVinte) : undefined;
        blocos.zeroPMeTrinta = blocos.zeroPMeTrinta ? dayjs(blocos.zeroPMeTrinta) : undefined;
        blocos.zeroPMeQuarenta = blocos.zeroPMeQuarenta ? dayjs(blocos.zeroPMeQuarenta) : undefined;
        blocos.zeroPMeCinquenta = blocos.zeroPMeCinquenta ? dayjs(blocos.zeroPMeCinquenta) : undefined;
        blocos.umAM = blocos.umAM ? dayjs(blocos.umAM) : undefined;
        blocos.umAMeDez = blocos.umAMeDez ? dayjs(blocos.umAMeDez) : undefined;
        blocos.umAMeVinte = blocos.umAMeVinte ? dayjs(blocos.umAMeVinte) : undefined;
        blocos.umAMeTrinta = blocos.umAMeTrinta ? dayjs(blocos.umAMeTrinta) : undefined;
        blocos.umAMeQuarenta = blocos.umAMeQuarenta ? dayjs(blocos.umAMeQuarenta) : undefined;
        blocos.umAMeCinquenta = blocos.umAMeCinquenta ? dayjs(blocos.umAMeCinquenta) : undefined;
        blocos.umPM = blocos.umPM ? dayjs(blocos.umPM) : undefined;
        blocos.umPMeDez = blocos.umPMeDez ? dayjs(blocos.umPMeDez) : undefined;
        blocos.umPMeVinte = blocos.umPMeVinte ? dayjs(blocos.umPMeVinte) : undefined;
        blocos.umPMeTrinta = blocos.umPMeTrinta ? dayjs(blocos.umPMeTrinta) : undefined;
        blocos.umPMeQuarenta = blocos.umPMeQuarenta ? dayjs(blocos.umPMeQuarenta) : undefined;
        blocos.umPMeCinquenta = blocos.umPMeCinquenta ? dayjs(blocos.umPMeCinquenta) : undefined;
        blocos.doisAM = blocos.doisAM ? dayjs(blocos.doisAM) : undefined;
        blocos.doisAMeDez = blocos.doisAMeDez ? dayjs(blocos.doisAMeDez) : undefined;
        blocos.doisAMeVinte = blocos.doisAMeVinte ? dayjs(blocos.doisAMeVinte) : undefined;
        blocos.doisAMeTrinta = blocos.doisAMeTrinta ? dayjs(blocos.doisAMeTrinta) : undefined;
        blocos.doisAMeQuarenta = blocos.doisAMeQuarenta ? dayjs(blocos.doisAMeQuarenta) : undefined;
        blocos.doisAMeCinquenta = blocos.doisAMeCinquenta ? dayjs(blocos.doisAMeCinquenta) : undefined;
        blocos.doisPM = blocos.doisPM ? dayjs(blocos.doisPM) : undefined;
        blocos.doisPMeDez = blocos.doisPMeDez ? dayjs(blocos.doisPMeDez) : undefined;
        blocos.doisPMeVinte = blocos.doisPMeVinte ? dayjs(blocos.doisPMeVinte) : undefined;
        blocos.doisPMeTrinta = blocos.doisPMeTrinta ? dayjs(blocos.doisPMeTrinta) : undefined;
        blocos.doisPMeQuarenta = blocos.doisPMeQuarenta ? dayjs(blocos.doisPMeQuarenta) : undefined;
        blocos.doisPMeCinquenta = blocos.doisPMeCinquenta ? dayjs(blocos.doisPMeCinquenta) : undefined;
        blocos.tresAM = blocos.tresAM ? dayjs(blocos.tresAM) : undefined;
        blocos.tresAMeDez = blocos.tresAMeDez ? dayjs(blocos.tresAMeDez) : undefined;
        blocos.tresAMeVinte = blocos.tresAMeVinte ? dayjs(blocos.tresAMeVinte) : undefined;
        blocos.tresAMeTrinta = blocos.tresAMeTrinta ? dayjs(blocos.tresAMeTrinta) : undefined;
        blocos.tresAMeQuarenta = blocos.tresAMeQuarenta ? dayjs(blocos.tresAMeQuarenta) : undefined;
        blocos.tresAMeCinquenta = blocos.tresAMeCinquenta ? dayjs(blocos.tresAMeCinquenta) : undefined;
        blocos.tresPM = blocos.tresPM ? dayjs(blocos.tresPM) : undefined;
        blocos.tresPMeDez = blocos.tresPMeDez ? dayjs(blocos.tresPMeDez) : undefined;
        blocos.tresPMeVinte = blocos.tresPMeVinte ? dayjs(blocos.tresPMeVinte) : undefined;
        blocos.tresPMeTrinta = blocos.tresPMeTrinta ? dayjs(blocos.tresPMeTrinta) : undefined;
        blocos.tresPMeQuarenta = blocos.tresPMeQuarenta ? dayjs(blocos.tresPMeQuarenta) : undefined;
        blocos.tresPMeCinquenta = blocos.tresPMeCinquenta ? dayjs(blocos.tresPMeCinquenta) : undefined;
        blocos.quatroAM = blocos.quatroAM ? dayjs(blocos.quatroAM) : undefined;
        blocos.quatroAMeDez = blocos.quatroAMeDez ? dayjs(blocos.quatroAMeDez) : undefined;
        blocos.quatroAMeVinte = blocos.quatroAMeVinte ? dayjs(blocos.quatroAMeVinte) : undefined;
        blocos.quatroAMeTrinta = blocos.quatroAMeTrinta ? dayjs(blocos.quatroAMeTrinta) : undefined;
        blocos.quatroAMeQuarenta = blocos.quatroAMeQuarenta ? dayjs(blocos.quatroAMeQuarenta) : undefined;
        blocos.quatroAMeCinquenta = blocos.quatroAMeCinquenta ? dayjs(blocos.quatroAMeCinquenta) : undefined;
        blocos.quatroPM = blocos.quatroPM ? dayjs(blocos.quatroPM) : undefined;
        blocos.quatroPMeDez = blocos.quatroPMeDez ? dayjs(blocos.quatroPMeDez) : undefined;
        blocos.quatroPMeVinte = blocos.quatroPMeVinte ? dayjs(blocos.quatroPMeVinte) : undefined;
        blocos.quatroPMeTrinta = blocos.quatroPMeTrinta ? dayjs(blocos.quatroPMeTrinta) : undefined;
        blocos.quatroPMeQuarenta = blocos.quatroPMeQuarenta ? dayjs(blocos.quatroPMeQuarenta) : undefined;
        blocos.quatroPMeCinquenta = blocos.quatroPMeCinquenta ? dayjs(blocos.quatroPMeCinquenta) : undefined;
        blocos.cincoAM = blocos.cincoAM ? dayjs(blocos.cincoAM) : undefined;
        blocos.cincoAMeDez = blocos.cincoAMeDez ? dayjs(blocos.cincoAMeDez) : undefined;
        blocos.cincoAMeVinte = blocos.cincoAMeVinte ? dayjs(blocos.cincoAMeVinte) : undefined;
        blocos.cincoAMeTrinta = blocos.cincoAMeTrinta ? dayjs(blocos.cincoAMeTrinta) : undefined;
        blocos.cincoAMeQuarenta = blocos.cincoAMeQuarenta ? dayjs(blocos.cincoAMeQuarenta) : undefined;
        blocos.cincoAMeCinquenta = blocos.cincoAMeCinquenta ? dayjs(blocos.cincoAMeCinquenta) : undefined;
        blocos.cincoPM = blocos.cincoPM ? dayjs(blocos.cincoPM) : undefined;
        blocos.cincoPMeDez = blocos.cincoPMeDez ? dayjs(blocos.cincoPMeDez) : undefined;
        blocos.cincoPMeVinte = blocos.cincoPMeVinte ? dayjs(blocos.cincoPMeVinte) : undefined;
        blocos.cincoPMeTrinta = blocos.cincoPMeTrinta ? dayjs(blocos.cincoPMeTrinta) : undefined;
        blocos.cincoPMeQuarenta = blocos.cincoPMeQuarenta ? dayjs(blocos.cincoPMeQuarenta) : undefined;
        blocos.cincoPMeCinquenta = blocos.cincoPMeCinquenta ? dayjs(blocos.cincoPMeCinquenta) : undefined;
        blocos.seisAM = blocos.seisAM ? dayjs(blocos.seisAM) : undefined;
        blocos.seisAMeDez = blocos.seisAMeDez ? dayjs(blocos.seisAMeDez) : undefined;
        blocos.seisAMeVinte = blocos.seisAMeVinte ? dayjs(blocos.seisAMeVinte) : undefined;
        blocos.seisAMeTrinta = blocos.seisAMeTrinta ? dayjs(blocos.seisAMeTrinta) : undefined;
        blocos.seisAMeQuarenta = blocos.seisAMeQuarenta ? dayjs(blocos.seisAMeQuarenta) : undefined;
        blocos.seisAMeCinquenta = blocos.seisAMeCinquenta ? dayjs(blocos.seisAMeCinquenta) : undefined;
        blocos.seisPM = blocos.seisPM ? dayjs(blocos.seisPM) : undefined;
        blocos.seisPMeDez = blocos.seisPMeDez ? dayjs(blocos.seisPMeDez) : undefined;
        blocos.seisPMeVinte = blocos.seisPMeVinte ? dayjs(blocos.seisPMeVinte) : undefined;
        blocos.seisPMeTrinta = blocos.seisPMeTrinta ? dayjs(blocos.seisPMeTrinta) : undefined;
        blocos.seisPMeQuarenta = blocos.seisPMeQuarenta ? dayjs(blocos.seisPMeQuarenta) : undefined;
        blocos.seisPMeCinquenta = blocos.seisPMeCinquenta ? dayjs(blocos.seisPMeCinquenta) : undefined;
        blocos.seteAM = blocos.seteAM ? dayjs(blocos.seteAM) : undefined;
        blocos.seteAMeDez = blocos.seteAMeDez ? dayjs(blocos.seteAMeDez) : undefined;
        blocos.seteAMeVinte = blocos.seteAMeVinte ? dayjs(blocos.seteAMeVinte) : undefined;
        blocos.seteAMeTrinta = blocos.seteAMeTrinta ? dayjs(blocos.seteAMeTrinta) : undefined;
        blocos.seteAMeQuarenta = blocos.seteAMeQuarenta ? dayjs(blocos.seteAMeQuarenta) : undefined;
        blocos.seteAMeCinquenta = blocos.seteAMeCinquenta ? dayjs(blocos.seteAMeCinquenta) : undefined;
        blocos.setePM = blocos.setePM ? dayjs(blocos.setePM) : undefined;
        blocos.setePMeDez = blocos.setePMeDez ? dayjs(blocos.setePMeDez) : undefined;
        blocos.setePMeVinte = blocos.setePMeVinte ? dayjs(blocos.setePMeVinte) : undefined;
        blocos.setePMeTrinta = blocos.setePMeTrinta ? dayjs(blocos.setePMeTrinta) : undefined;
        blocos.setePMeQuarenta = blocos.setePMeQuarenta ? dayjs(blocos.setePMeQuarenta) : undefined;
        blocos.setePMeCinquenta = blocos.setePMeCinquenta ? dayjs(blocos.setePMeCinquenta) : undefined;
        blocos.oitoAM = blocos.oitoAM ? dayjs(blocos.oitoAM) : undefined;
        blocos.oitoAMeDez = blocos.oitoAMeDez ? dayjs(blocos.oitoAMeDez) : undefined;
        blocos.oitoAMeVinte = blocos.oitoAMeVinte ? dayjs(blocos.oitoAMeVinte) : undefined;
        blocos.oitoAMeTrinta = blocos.oitoAMeTrinta ? dayjs(blocos.oitoAMeTrinta) : undefined;
        blocos.oitoAMeQuarenta = blocos.oitoAMeQuarenta ? dayjs(blocos.oitoAMeQuarenta) : undefined;
        blocos.oitoAMeCinquenta = blocos.oitoAMeCinquenta ? dayjs(blocos.oitoAMeCinquenta) : undefined;
        blocos.oitoPM = blocos.oitoPM ? dayjs(blocos.oitoPM) : undefined;
        blocos.oitoPMeDez = blocos.oitoPMeDez ? dayjs(blocos.oitoPMeDez) : undefined;
        blocos.oitoPMeVinte = blocos.oitoPMeVinte ? dayjs(blocos.oitoPMeVinte) : undefined;
        blocos.oitoPMeTrinta = blocos.oitoPMeTrinta ? dayjs(blocos.oitoPMeTrinta) : undefined;
        blocos.oitoPMeQuarenta = blocos.oitoPMeQuarenta ? dayjs(blocos.oitoPMeQuarenta) : undefined;
        blocos.oitoPMeCinquenta = blocos.oitoPMeCinquenta ? dayjs(blocos.oitoPMeCinquenta) : undefined;
        blocos.noveAM = blocos.noveAM ? dayjs(blocos.noveAM) : undefined;
        blocos.noveAMeDez = blocos.noveAMeDez ? dayjs(blocos.noveAMeDez) : undefined;
        blocos.noveAMeVinte = blocos.noveAMeVinte ? dayjs(blocos.noveAMeVinte) : undefined;
        blocos.noveAMeTrinta = blocos.noveAMeTrinta ? dayjs(blocos.noveAMeTrinta) : undefined;
        blocos.noveAMeQuarenta = blocos.noveAMeQuarenta ? dayjs(blocos.noveAMeQuarenta) : undefined;
        blocos.noveAMeCinquenta = blocos.noveAMeCinquenta ? dayjs(blocos.noveAMeCinquenta) : undefined;
        blocos.novePM = blocos.novePM ? dayjs(blocos.novePM) : undefined;
        blocos.novePMeDez = blocos.novePMeDez ? dayjs(blocos.novePMeDez) : undefined;
        blocos.novePMeVinte = blocos.novePMeVinte ? dayjs(blocos.novePMeVinte) : undefined;
        blocos.novePMeTrinta = blocos.novePMeTrinta ? dayjs(blocos.novePMeTrinta) : undefined;
        blocos.novePMeQuarenta = blocos.novePMeQuarenta ? dayjs(blocos.novePMeQuarenta) : undefined;
        blocos.novePMeCinquenta = blocos.novePMeCinquenta ? dayjs(blocos.novePMeCinquenta) : undefined;
        blocos.dezAM = blocos.dezAM ? dayjs(blocos.dezAM) : undefined;
        blocos.dezAMeDez = blocos.dezAMeDez ? dayjs(blocos.dezAMeDez) : undefined;
        blocos.dezAMeVinte = blocos.dezAMeVinte ? dayjs(blocos.dezAMeVinte) : undefined;
        blocos.dezAMeTrinta = blocos.dezAMeTrinta ? dayjs(blocos.dezAMeTrinta) : undefined;
        blocos.dezAMeQuarenta = blocos.dezAMeQuarenta ? dayjs(blocos.dezAMeQuarenta) : undefined;
        blocos.dezAMeCinquenta = blocos.dezAMeCinquenta ? dayjs(blocos.dezAMeCinquenta) : undefined;
        blocos.dezPM = blocos.dezPM ? dayjs(blocos.dezPM) : undefined;
        blocos.dezPMeDez = blocos.dezPMeDez ? dayjs(blocos.dezPMeDez) : undefined;
        blocos.dezPMeVinte = blocos.dezPMeVinte ? dayjs(blocos.dezPMeVinte) : undefined;
        blocos.dezPMeTrinta = blocos.dezPMeTrinta ? dayjs(blocos.dezPMeTrinta) : undefined;
        blocos.dezPMeQuarenta = blocos.dezPMeQuarenta ? dayjs(blocos.dezPMeQuarenta) : undefined;
        blocos.dezPMeCinquenta = blocos.dezPMeCinquenta ? dayjs(blocos.dezPMeCinquenta) : undefined;
        blocos.onzeAM = blocos.onzeAM ? dayjs(blocos.onzeAM) : undefined;
        blocos.onzeAMeDez = blocos.onzeAMeDez ? dayjs(blocos.onzeAMeDez) : undefined;
        blocos.onzeAMeVinte = blocos.onzeAMeVinte ? dayjs(blocos.onzeAMeVinte) : undefined;
        blocos.onzeAMeTrinta = blocos.onzeAMeTrinta ? dayjs(blocos.onzeAMeTrinta) : undefined;
        blocos.onzeAMeQuarenta = blocos.onzeAMeQuarenta ? dayjs(blocos.onzeAMeQuarenta) : undefined;
        blocos.onzeAMeCinquenta = blocos.onzeAMeCinquenta ? dayjs(blocos.onzeAMeCinquenta) : undefined;
        blocos.onzePM = blocos.onzePM ? dayjs(blocos.onzePM) : undefined;
        blocos.onzePMeDez = blocos.onzePMeDez ? dayjs(blocos.onzePMeDez) : undefined;
        blocos.onzePMeVinte = blocos.onzePMeVinte ? dayjs(blocos.onzePMeVinte) : undefined;
        blocos.onzePMeTrinta = blocos.onzePMeTrinta ? dayjs(blocos.onzePMeTrinta) : undefined;
        blocos.onzePMeQuarenta = blocos.onzePMeQuarenta ? dayjs(blocos.onzePMeQuarenta) : undefined;
        blocos.onzePMeCinquenta = blocos.onzePMeCinquenta ? dayjs(blocos.onzePMeCinquenta) : undefined;
      });
    }
    return res;
  }
}
