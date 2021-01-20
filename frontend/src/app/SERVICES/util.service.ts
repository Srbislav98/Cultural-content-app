import { CompileShallowModuleMetadata } from '@angular/compiler';
import { Injectable } from '@angular/core';

@Injectable({
	providedIn: 'root'
})
export class UtilService {

	constructor() { }

	public getNoPages(totalItems: number, pageSize: number): number {
		console.log("samo");
		console.log(totalItems);
		console.log(pageSize);
		console.log(Math.ceil(totalItems / pageSize));
		return Math.ceil(totalItems / pageSize);
	}
}
