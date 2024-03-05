const xmlrpc = require('xmlrpc');

// Odoo connection parameters
const odooUrl = 'localhost';
const odooPort = 8069; // Default Odoo port
const odooDb = 'odoo_BIEL_PALOMAR';
const odooUsername = 'a22biepalgon@inspedralbes.cat';
const odooPassword = 'a22biepalgon';

// XML-RPC client
const odooClient = xmlrpc.createSecureClient({
    host: odooUrl,
    port: odooPort,
    path: '/xmlrpc/2/common',
});

// Authenticate and get user ID
odooClient.methodCall('authenticate', [odooDb, odooUsername, odooPassword, {}], (error, uid) => {
    if (error) {
        console.error('Authentication failed:', error);
    } else {
        console.log('Authenticated successfully. User ID:', uid);

        // Create XML-RPC client for object operations
        const odooObjectClient = xmlrpc.createSecureClient({
            host: odooUrl,
            port: odooPort,
            path: '/xmlrpc/2/object',
        });

        const newProductData = {
            name: 'ProducteProva',
            default_code: '1',
            type: 'product',
            categ_id: 1, // Category ID (replace with actual category ID)
        };

        // Create product
        odooObjectClient.methodCall('execute_kw', [odooDb, uid, odooPassword, 'product.product', 'create', [newProductData]], (error, productId) => {
            if (error) {
                console.error('Product creation failed:', error);
            } else {
                console.log('Product created successfully. Product ID:', productId);
            }
        });
    }
});
