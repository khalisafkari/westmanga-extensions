import module from './module';

export default async ():Promise<{
  country: string;
  city: string;
  lat: number;
  lon: number;
}> => {
  return module.getCountry()
}
