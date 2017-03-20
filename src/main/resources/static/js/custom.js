$( document ).ready(function() {
    var url = window.location;

    // for sidebar menu entirely but not cover treeview
    $('ul.sidebar-menu a').filter(function() {
      return this.href == url;
    }).parent().addClass('active');

    $('ul.treeview-menu a').filter(function() {
      return this.href == url;
    }).closest('.treeview').addClass('active');
})
