import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { FitnessProgram, FitnessProgramArray } from '../models/fitness-program';
import { Comment } from '../models/comment';
import { Kategorija } from '../models/kategorija';

@Injectable({
  providedIn: 'root'
})
export class ProgramService {

  private url = 'http://localhost:8080/program';

  constructor(private httpClient: HttpClient) { }
  
  getProgram(id: number): Observable<FitnessProgram> {
    return this.httpClient.get<FitnessProgram>(this.url + `/${id}`);
  }

  getAllPrograms(): Observable<FitnessProgram[]> {
    return this.httpClient.get<FitnessProgram[]>(this.url);
  }

  getProgramsAsPlainArray(page: number, size: number): Observable<FitnessProgramArray> {
    const params = new HttpParams()
      .set('page', page.toString())
      .set('size', size.toString());
      return this.httpClient.get<FitnessProgramArray>(this.url+'/paginated', { params });
  }

  getPrograms(page: number, size: number): Observable<any> {
    const params = new HttpParams()
      .set('page', page.toString())
      .set('size', size.toString());
      return this.httpClient.get<any>(this.url+'/paginated', { params });
  }

  uploadProgramAndImage(fitnessProgram: FitnessProgram, image: File): Observable<FitnessProgram> {
    const formData = new FormData();
    formData.append('program', JSON.stringify(fitnessProgram)); // Convert data to JSON string and append to FormData
    formData.append('image', image); // Append image file to FormData

    // Set headers if needed (e.g., for authorization)
    //const headers = new HttpHeaders();
    // headers.append('Authorization', 'Bearer <token>');

     return this.httpClient.post<FitnessProgram>(this.url+"/upload", formData);
  }

  updateProgram(fitnessProgram: FitnessProgram, image: File): Observable<FitnessProgram> {
    if (image != null) {
      const formData = new FormData();
      formData.append('program', JSON.stringify(fitnessProgram)); // Convert data to JSON string and append to FormData
      formData.append('image', image); // Append image file to FormData
      return this.httpClient.put<FitnessProgram>(this.url, formData);
    }
    else
      return this.httpClient.put<FitnessProgram>(`${this.url}/${fitnessProgram.programId}`, fitnessProgram);    
  }

  deleteProgram(id : number): Observable<string> {
    return this.httpClient.delete<string>(`${this.url}/${id}`);
  }

  uploadComment(komentar : Comment) {
    return this.httpClient.post<FitnessProgram>('http://localhost:8080/komentar', komentar);
  }

  getCategories() : Observable<Kategorija[]> {
    const url = 'http://localhost:8080/kategorija';
    return this.httpClient.get<Kategorija[]>(url);
  }
}
