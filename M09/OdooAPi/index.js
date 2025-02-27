const xmlrpc = require('xmlrpc');

// Odoo connection parameters
const odooUrl = 'localhost';
const odooPort = 8069; // Default Odoo port
const odooDb = 'odoo_BIEL_PALOMAR';
const odooUsername = 'a22biepalgon@inspedralbes.cat';
const odooPassword = 'a22biepalgon';

// XML-RPC client
const odooClient = xmlrpc.createClient({
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
        const odooObjectClient = xmlrpc.createClient({
            host: odooUrl,
            port: odooPort,
            path: '/xmlrpc/2/object',
        });

        const newProductData = {
            name: 'Producte Prova2',
            default_code: 'Samarreta',
            type: 'product',
            categ_id: 1,
            x_colorSamarreta: 'Negre',
            list_price: Math.floor(Math.random() * (100 - 10 + 1)) + 10,
            standard_price: Math.floor(Math.random() * (50 - (10) + 1)) + 10 
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
