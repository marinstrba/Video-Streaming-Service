# Stage 1: Build the Angular application
FROM node:18-alpine as build
WORKDIR /app
COPY ./Frontend/video-streaming-service-ui/package.json ./
COPY ./Frontend/video-streaming-service-ui/package-lock.json* ./
RUN npm install
COPY ./Frontend/video-streaming-service-ui/ ./
RUN npm run build

# Stage 2: Serve the application with Nginx
FROM nginx:alpine
COPY --from=build /app/dist/video-streaming-service-ui /usr/share/nginx/html
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
