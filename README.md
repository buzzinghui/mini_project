Final Assignment Modules



TOOLS

-> Spring Security

-> Redis

-> Stripe

-> Email SMTP 



MODULES

[1. Authentication] 

-> (POST) /api/auth/v1/login: Login - Customer + Admin

-> (POST) /api/auth/v1/logout: Logout - Customer + Admin -> Need Bearer Token

-> (PATCH) /api/auth/v1/password: Change password - Customer + Admin -> Need Bearer Token

[2. Product]

-> (GET) /api/product/v1: Get product list and details - Customer + Admin -> Need Bearer Token

-> (POST) /api/admin/product/v1: Create new product - Admin -> Need Bearer Token

-> (POST) /api/admin/product/v1/image: Upload product image - Admin -> Need Bearer Token

-> (PATCH) /api/admin/product/v1: Update existing product - Admin -> Need Bearer Token

-> (DELETE) /api/admin/product/v1: Delete product - Admin -> Need Bearer Token


[3. User]

-> (GET) /api/user/v1/details: Get user details - Admin + Customer  -> Need Bearer Token

-> (POST) /api/user/v1/register: Register new user  - Customer 

-> (PATCH) /api/user/v1/details: Update details - Customer  -> Need Bearer Token


[4. Cart]

-> (Customer) Get cart products 

-> (Customer) Add product to cart 

-> (Customer) Delete product from cart

-> (Customer) Update product in cart


[5. Order]

-> (Customer) Get order history

-> (Customer) Get order details

-> (Customer) Get pending orders for payment 

-> (Customer) Get confirmed orders for delivery 

-> (Customer) Get orders that are delivered


[6. Payment]

-> (Customer) Get payment redirection to Stripe to make payment

-> (Customer) Websocket to listen to payment status 

-> (Customer) Send stripe receipt to customer upon successful payment 


[7. Notification]

-> (Admin) Send email to customer to notify them of upcoming promotion


