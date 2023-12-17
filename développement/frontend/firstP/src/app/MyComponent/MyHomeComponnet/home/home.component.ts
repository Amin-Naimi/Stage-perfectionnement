
import { Component, OnInit } from '@angular/core';
import 'jquery.marquee';

declare var $: any;

@Component({
selector: 'app-home',
templateUrl: './home.component.html',
styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  ngOnInit() {
    $('.marquee').marquee({
       //duration in milliseconds of the marquee
    	duration: 30000,
    	//gap in pixels between the tickers
    	 delayBeforeStart: 0,
        //'left' or 'right'
        direction: 'left',
        //true or false - should the marquee be duplicated to show an effect of continues flow
        duplicated: true,
        pauseOnHover: true,
        startVisible: true

    });
  }
}