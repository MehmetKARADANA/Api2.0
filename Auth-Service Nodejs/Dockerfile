# 1. Node.js'in resmi bir imajını kullanarak bir temel katman oluştur
FROM node:18

# 2. Uygulama dizinini oluştur
WORKDIR /app

# 3. Paketleri kopyala ve yükle
COPY package*.json ./
RUN npm install

# 4. Uygulama dosyalarını kopyala
COPY . .

# 5. Uygulama için ortam değişkeni ayarla (isteğe bağlı)
ENV PORT=8084

# 6. Konteyner dışına açılacak portu belirle
EXPOSE 8084

# 7. Uygulamayı başlat
CMD ["npm", "start"]
