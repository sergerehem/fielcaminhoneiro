      </div><!-- /#page-wrapper -->

    </div><!-- /#wrapper -->

    <!-- JavaScript -->
    <script src="/js/jquery-1.10.2.js"></script>
    <script src="/js/bootstrap.js"></script>

    <!-- Page Specific Plugins -->
    <script src="/js/tablesorter/jquery.tablesorter.js"></script>
    <script src="/js/tablesorter/tables.js"></script>

		<!-- Masked Input -->
		<script src="/js/jquery.maskedinput.min.js" type="text/javascript"></script>
    <script type="text/javascript">
		jQuery(function(jQuery){
			 jQuery(".phone").mask("(99)9999-9999?");
		});
    </script> 

    <!-- Choosen plugin -->
    <script src="/js/chosen.jquery.min.js" type="text/javascript"></script>
    <script type="text/javascript">
      var config = {
        '.chosen-select'           : {},
        '.chosen-select-deselect'  : {allow_single_deselect:true},
        '.chosen-select-no-single' : {disable_search_threshold:10},
        '.chosen-select-no-results': {no_results_text:'Oops, nothing found!'},
        '.chosen-select-width'     : {width:"95%"}
      }
      for (var selector in config) {
        (jQuery)(selector).chosen(config[selector]);
      }
    </script>  

		<!-- Footable -->
    <script src="/footable/js/footable.min.js" type="text/javascript"></script>
    <script src="/footable/js/footable.filter.min.js" type="text/javascript"></script>
    <script src="/footable/js/footable.sort.min.js" type="text/javascript"></script>
    <script src="/footable/js/footable.paginate.min.js" type="text/javascript"></script>
    <script type="text/javascript">
      (jQuery)(function() {
        (jQuery)('table.footable').footable();
      });
    </script> 

    <!-- Data Table -->
    <link href="/datatable/css/jquery.dataTables.css">
		<script type="text/javascript" language="javascript" src="/datatable/js/jquery.dataTables.js"></script>
		<script type="text/javascript" charset="utf-8">
			(jQuery)(document).ready(function() {
				(jQuery)('.datatable').dataTable();
			} );
		</script>

		<!-- Graph
    <script src="/js/raphael-min.js"></script>
    <script src="/js/morris/morris-0.4.3.min.js"></script>
    <script src="/js/morris/chart-data-morris.js"></script>
		-->
  </body>
</html>