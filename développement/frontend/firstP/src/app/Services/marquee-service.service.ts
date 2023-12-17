import { Injectable } from '@angular/core';
import 'jquery.marquee';

declare var $: any;

@Injectable({
  providedIn: 'root'
})
export class MarqueeServiceService {
 initializeMarquee() {
    $('.marquee').marquee({
      duration: 30000,
      delayBeforeStart: 0,
      direction: 'left',
      duplicated: true,
      pauseOnHover: true,
      startVisible: true
    });
  }
}
