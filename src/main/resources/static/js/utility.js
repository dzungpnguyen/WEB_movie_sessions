function confirmDeleteFilm(button) {
  var filmId = button.getAttribute("data-filmid");
  var filmTitle = button.getAttribute("data-filmtitle");
  
  if (confirm(`Confirm your action: DELETE film ${filmTitle} with id ${filmId}`)) {
    // Perform delete action using AJAX
    $.ajax({
      url: `/uploader/delete/${filmId}`,
      method: 'DELETE',
      success: function() {
        // Display success notification
        showNotification("notification-delete-success", `Film ${filmId} deleted successfully`);
        
        // Reload the page to reflect the updated film table
        // window.location.reload();
        updateFilmTable();
      },
      error: function() {
        showNotification("notification-delete-failure", `Error while deleting film ${filmId}`);
      }
    });
  }
}

function updateFilmTable() {
  // Perform AJAX request to fetch the updated film table content
  $.ajax({
    url: '/uploader/my-films',
    method: 'GET',
    success: function(response) {
      // Extract the film table content from the response
      var filmTableContent = $(response).find('#film-table').html();
      
      // Update the film table content with the extracted content
      $('#film-table').html(filmTableContent);
      
    // Extract the message from the response
      var message = $(response).find('#film-table-info').text();
      
      // Update the film table info message with the extracted message
      $('#film-table-info').text(message);
    },
    error: function() {
      showNotification("notification-updating-failure", 'Error while updating film table');
    }
  });
}

function showNotification(idHtml, message) {
  // Get the notification element by its ID
  var notification = document.getElementById(idHtml);
  
  // Set the notification message
  notification.textContent = message;
  
  // Show the notification
  notification.style.display = 'block';
  
  // Hide the notification after 3 seconds (adjust as needed)
  setTimeout(function() {
    notification.style.display = 'none';
  }, 3000);
}
