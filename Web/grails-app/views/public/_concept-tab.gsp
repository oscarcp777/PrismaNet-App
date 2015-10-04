<section>
    <ul class="nav nav-tabs padding-12 tab-color-blue background-blue">
        <li ng-class="{ active:tab.isSet(1) }">
            <a href ng-click="tab.setTab(1)">
             <i class="ace-icon fa fa-twitter bigger-110"></i>
            Tweet</a>
        </li>
        <li ng-class="{ active:tab.isSet(2) }">
            <a href ng-click="tab.setTab(2)">
             <i class="ace-icon fa fa-cloud bigger-110"></i>
            Nube
            </a>
        </li>
    </ul>
</section>

<div class="tab-content">
	<div class="tab-pane" ng-show="tab.isSet(1)" ng-class="{active:tab.isSet(1) }">
	<div class="alert alert-info">
        <ul class="fa-ul">
          <li>
            <i class="fa fa-info-circle fa-lg fa-li"></i>
            Tweets más relevantes por Autor
          </li>
        </ul>
      </div>
	    
		 <div class="center-block size-list">
	     <list-tweets ></list-tweets>
	     </div>
	</div>
	<div class="tab-pane" ng-show="tab.isSet(2)" ng-class="{active:tab.isSet(2) }">
	 	<div class="alert alert-info">
        <ul class="fa-ul">
          <li>
            <i class="fa fa-info-circle fa-lg fa-li"></i>
          Nube con las palabras mas utlizadas en los Tweets
          </li>
        </ul>
      </div>
		 <div id="cloudWordsTW" style="min-height:350px;"></div>
	</div>
</div>