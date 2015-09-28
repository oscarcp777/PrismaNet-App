<section>
    <ul class="nav nav-tabs padding-12 tab-color-blue background-blue">
        <li ng-class="{ active:tab.isSet(1) }">
            <a href ng-click="tab.setTab(1)">Tweets más relevantes por Author</a>
        </li>
        <li ng-class="{ active:tab.isSet(2) }">
            <a href ng-click="tab.setTab(2)">Nube de palabras</a>
        </li>
    </ul>
</section>

<div class="tab-content">
	<div class="tab-pane" ng-show="tab.isSet(1)" ng-class="{active:tab.isSet(1) }">
		 <div class="center-block size-list">
	     <list-tweets ></list-tweets>
	     </div>
	</div>
	<div class="tab-pane" ng-show="tab.isSet(2)" ng-class="{active:tab.isSet(2) }">
		 <div id="cloudWordsTW" style="min-height:350px;"></div>
	</div>
</div>