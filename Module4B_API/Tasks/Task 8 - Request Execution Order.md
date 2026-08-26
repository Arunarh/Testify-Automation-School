# Task 8 - Request Execution Order

## Request Execution Order

The App Status request is configured with a pre-request script to execute the Place Order request next.

The Place Order request is configured to stop the collection after execution.

### Pre-request Script - App Status

```javascript
pm.execution.setNextRequest("Task 4 - Place Order");

### Post-response Script - Task 4 - Place Order

```javascript
pm.execution.setNextRequest(null);

