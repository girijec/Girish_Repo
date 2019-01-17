import { Component, OnInit, OnDestroy, ChangeDetectorRef, NgModule } from '@angular/core';
import { Router } from '@angular/router';
import { FormsModule, FormBuilder, FormGroup } from '@angular/forms';
import { MediaMatcher } from '@angular/cdk/layout';


@Component({
    selector: 'app-layout',
    templateUrl: './layout.component.html',
    styleUrls: ['./layout.component.scss']
})
export class LayoutComponent implements OnInit, OnDestroy{
    mobileQuery: MediaQueryList;
    options: FormGroup;

    private _mobileQueryListener: () => void;

    constructor(public router: Router,
        fb: FormBuilder,
        changeDetectorRef: ChangeDetectorRef,
        media: MediaMatcher) {
        this.mobileQuery = media.matchMedia('(max-width: 600px)');
        this._mobileQueryListener = () => changeDetectorRef.detectChanges();
        this.mobileQuery.addListener(this._mobileQueryListener);

        this.options = fb.group({
            'fixed': false,
            'top': 0,
            'bottom': 0,
        });
    }

    ngOnInit() {
        if (this.router.url === '/') {
            this.router.navigate(['/dashboard']);
        }
    }

    ngOnDestroy(): void {
        this.mobileQuery.removeListener(this._mobileQueryListener);
    }

    get title(): any {
        return localStorage.getItem('title');
    }


}
