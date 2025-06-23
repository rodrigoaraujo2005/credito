# Stage 1: Build the Angular application
FROM node:14 as build

WORKDIR /app

# Copy package.json and package-lock.json
COPY package*.json ./

# Install dependencies
RUN npm install -g @angular/cli@12


RUN npm ci

COPY . .

# Verificar e corrigir estrutura de environments
RUN mkdir -p src/environments
RUN if [ ! -f "src/environments/environment.ts" ]; then \
      echo "export const environment = { production: false, apiUrl: 'http://localhost:8080'  };" > src/environments/environment.ts; \
    fi
RUN if [ ! -f "src/environments/environment.prod.ts" ]; then \
      echo "export const environment = { production: true, apiUrl: 'http://backend:8080' };" > src/environments/environment.prod.ts; \
    fi


RUN ng build --configuration production || ng build --prod || ng build



# Stage 2: Serve the application with Nginx
FROM nginx:1.21

# Copy the build output to replace the default nginx contents
COPY --from=build /app/dist/credit-frontend /usr/share/nginx/html

# Copy custom nginx config
COPY nginx.conf /etc/nginx/nginx.conf

# Expose port 80
EXPOSE 80

# Start Nginx server
CMD ["nginx", "-g", "daemon off;"]
