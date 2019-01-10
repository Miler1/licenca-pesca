
/**
 * Métodos utilitários para manipulação de arquivos
 */

export default {
	
	// Convert um arquivo base64 para BLOB
	convertBase64ToBlob (base64) {
	
		let len = base64.length;
						
		let bytes = new Uint8Array(len);
						
		for (var i = 0; i < len; i++) {
			bytes[i] = base64.charCodeAt(i);
		}

		return new Blob([bytes], {type: 'application/pdf'});
	}
};
