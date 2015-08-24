<section>
    <ul class="nav nav-tabs padding-12 tab-color-blue background-blue">
        <li ng-class="{ active:tab.isSet($index +1) }" ng-repeat='(key, value) in report.mapTweets'>
            <a href ng-click="tab.setTab($index +1)">{{key}}</a>
        </li>
    </ul>
</section>

<div class="tab-content">
	<div ng-show="tab.isSet($index +1)" ng-class="{ tab-pane active:tab.isSet($index +1) }" 
		 ng-repeat='(key, listTweets) in report.mapTweets'>
	     <list-tweets ></list-tweets>
	</div>
</div>